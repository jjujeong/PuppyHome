package org.example.puppyhome.notification;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import nonapi.io.github.classgraph.json.Id;

@Entity
public class User {

    @Id
    private String userId;

    @Embedded
    private AnimalFilter filter;

    private String appId;
    private String phone;
    private String email;
    private boolean notifyEnabled;
    @jakarta.persistence.Id
    private Long id;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AnimalFilter getFilter() {
        return filter;
    }

    public void setFilter(AnimalFilter filter) {
        this.filter = filter;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNotifyEnabled() {
        return notifyEnabled;
    }

    public void setNotifyEnabled(boolean notifyEnabled) {
        this.notifyEnabled = notifyEnabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void getId(Long id) {
        this.id = id;
    }
}