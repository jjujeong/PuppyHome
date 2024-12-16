package org.example.puppyhome.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPreferencesService {
    private final UserPreferencesRepository userPreferencesRepository;

    @Autowired
    public UserPreferencesService(UserPreferencesRepository userPreferencesRepository) {
        this.userPreferencesRepository = userPreferencesRepository;
    }


    public void updatePreferences(String userId, AnimalFilter animalFilter, boolean alarmSend, String email, String phone, String appId) {
        UserPreferences userPreferences = userPreferencesRepository.findByUserId(userId).orElse(null);

        if (userPreferences != null) {
            userPreferences.setAnimalFilter(animalFilter);
            userPreferences.setAlarmSend(alarmSend);
            userPreferencesRepository.save(userPreferences);
        } else {
            UserPreferences newPreferences = new UserPreferences(userId, animalFilter, alarmSend, email, phone, appId);
            userPreferencesRepository.save(newPreferences);
        }
    }

    public AnimalFilter getPreferences(String userId) {
        return userPreferencesRepository.findByUserId(userId)
                .map(UserPreferences::getAnimalFilter)
                .orElse(null);
    }

    public Iterable<UserPreferences> getUsersWithAlarmSendTrue() {
        return userPreferencesRepository.findByAlarmSend(true);
    }

}