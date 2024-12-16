package org.example.puppyhome.notification;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_preferences")
public class UserPreferences {
    @Id
    private String userId;
    private AnimalFilter animalFilter;
    private boolean alarmSend;
    private String email;
    private String phone;
    private String appId;

    public UserPreferences(String userId, AnimalFilter animalFilter, boolean alarmSend, String email, String phone, String appId) {
        this.userId = userId;
        this.animalFilter = animalFilter;
        this.alarmSend = alarmSend;
        this.email = email;
        this.phone = phone;
        this.appId = appId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AnimalFilter getAnimalFilter() {
        return animalFilter;
    }

    public void setAnimalFilter(AnimalFilter animalFilter) {
        this.animalFilter = animalFilter;
    }

    public boolean isAlarmSend() {
        return alarmSend;
    }

    public void setAlarmSend(boolean alarmSend) {
        this.alarmSend = alarmSend;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "UserPreferences{" +
                "userId='" + userId + '\'' +
                ", animalFilter=" + animalFilter +
                ", alarmSend=" + alarmSend +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", appId='" + appId + '\'' +
                '}';
    }
}