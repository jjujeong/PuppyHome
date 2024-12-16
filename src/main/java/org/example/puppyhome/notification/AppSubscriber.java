package org.example.puppyhome.notification;

import org.example.puppyhome.dbstorage.model.Animal;

import java.util.List;

public class AppSubscriber implements Subscriber {
    private String appId;

    public AppSubscriber(String appId) {
        this.appId = appId;
    }

    @Override
    public String notify(List<Animal> animals) {
        StringBuilder notificationMessage = new StringBuilder();
        notificationMessage.append("앱으로 알림을 전송합니다. ").append(appId).append("\n");

        if (animals.isEmpty()) {

        } else {
            notificationMessage.append("입력하신 정보의 동물이 있습니다. \n");
            animals.forEach(animal -> notificationMessage.append(animal.toString()).append("\n"));
        }

        return notificationMessage.toString();
    }
}