package org.example.puppyhome.notification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "선호 동물 정보 입력 및 알림 발송 기능")
public class MainController {
    private final UserPreferencesService userPreferencesService;
    private final AlarmManager alarmManager = new AlarmManager();

    public MainController(UserPreferencesService userPreferencesService) {
        this.userPreferencesService = userPreferencesService;
    }

    @PostMapping("/alarm/preferences")
    @Operation(description = "알림을 받고 싶은 선호하는 동물의 정보를 입력합니다. 알림을 받고 싶지 않다면 false로 입력하면 됩니다.")
    public ResponseEntity<String> savePreferences(
            @RequestParam String userId,
            @RequestParam boolean alarmSend,
            @RequestParam(required = false) String appId,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) String intakeAge,
            @RequestParam(required = false) String furColor,
            @RequestParam(required = false) String gender){

        AnimalFilter animalFilter = new AnimalFilter()
                .setType(type)
                .setBreed(breed)
                .setIntakeAge(intakeAge)
                .setFurColor(furColor)
                .setGender(gender);

        if (phone != null && !phone.isEmpty()) {
            alarmManager.subscribe(new PhoneSubscriber(phone));
        }
        if (email != null && !email.isEmpty()) {
            alarmManager.subscribe(new EmailSubscriber(email));
        }
        if (appId != null && !appId.isEmpty()) {
            alarmManager.subscribe(new AppSubscriber(appId));
        }

        userPreferencesService.updatePreferences(userId, animalFilter, alarmSend, email, phone, appId);

        return ResponseEntity.ok("사용자 선호 조건이 저장되었습니다.");
    }


    @PostMapping("/alarm/send")
    @Operation(description = "버튼을 누르면 알림을 받기를 원하는 사용자들에게 알림이 전송됩니다.")
    public ResponseEntity<String> sendAlarm() {
        Iterable<UserPreferences> users = userPreferencesService.getUsersWithAlarmSendTrue();

        // 기존 구독정보를 불러와서 알람 매니저에 갱신
        users.forEach(user -> {

            String phone = user.getPhone();
            String email = user.getEmail();
            String appId = user.getAppId();

            if (phone != null && !phone.isEmpty()) {
                if (!alarmManager.checkExists(phone)) {
                    alarmManager.subscribe(new PhoneSubscriber(phone));
                }
            }
            if (email != null && !email.isEmpty()) {
                if (!alarmManager.checkExists(email)) {
                    alarmManager.subscribe(new EmailSubscriber(email));
                }
            }
            if (appId != null && !appId.isEmpty()) {
                if (!alarmManager.checkExists(appId)) {
                    alarmManager.subscribe(new AppSubscriber(appId));
                }
            }
        });

        StringBuilder notificationResult = new StringBuilder();


        users.forEach(userPreferences -> {
            String result = alarmManager.notifyAlarmOn(userPreferences);
            System.out.println("result: " + result);
            notificationResult.append("User ID: ").append(userPreferences.getUserId()).append("\n").append(result).append("\n");
        });

        if (notificationResult.length() == 0) {
            return ResponseEntity.ok("알림을 받을 사용자가 없습니다.");
        }

        return ResponseEntity.ok("알림이 발송되었습니다: \n" + notificationResult.toString());
    }
}