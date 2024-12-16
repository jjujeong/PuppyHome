package org.example.puppyhome.notification;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserPreferencesRepository extends MongoRepository<UserPreferences, String> {
    Optional<UserPreferences> findByUserId(String userId);
    Iterable<UserPreferences> findByAlarmSend(boolean alarmSend);
}