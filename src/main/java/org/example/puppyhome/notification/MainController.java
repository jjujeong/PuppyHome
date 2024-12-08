package org.example.puppyhome.notification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
    @Tag(name = "새로운 유기동물 알림 기능")
    @GetMapping("/alarm")
    @Operation(summary = "데이터 크롤링", description = "내가 원하는 동물 정보에 맞는 동물이 입력되면 동물 정보가 알림으로 옵니다.")
    public ResponseEntity<String> alarm(
            @RequestParam(required = false) String appId,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) String intakeAgeStart,
            @RequestParam(required = false) String intakeAgeEnd,
            @RequestParam(required = false) String furColor,
            @RequestParam(required = false) String gender) {

        AlarmManager alarmManager = new AlarmManager();

        // 입력값이 있을 경우에만 구독자 추가
        if (phone != null && !phone.isEmpty()) {
            alarmManager.subscribe(new PhoneSubscriber(phone));
        }
        if (email != null && !email.isEmpty()) {
            alarmManager.subscribe(new EmailSubscriber(email));
        }
        if (appId != null && !appId.isEmpty()) {
            alarmManager.subscribe(new AppSubscriber(appId));
        }

        // 필터 조건 설정
        AnimalFilter animalFilter = new AnimalFilter()
                .setType(type)
                .setBreed(breed)
                .setIntakeAge(intakeAgeStart, intakeAgeEnd)
                .setFurColor(furColor)
                .setGender(gender);

        // 알림 발송
        String notificationResult = alarmManager.notifyAlarmOn(animalFilter);

        return ResponseEntity.ok("알림이 발송되었습니다: " + notificationResult);
    }
}