package org.example.puppyhome.notification;

import org.example.puppyhome.dbstorage.model.Animal;

import java.util.List;

public class EmailSubscriber implements Subscriber {
    private String email;

    public EmailSubscriber(String appId) {
        this.email = email;
    }

    @Override
    public String notify(List<Animal> animals) {
        StringBuilder notificationMessage = new StringBuilder();
        notificationMessage.append("이메일로 데이터를 전송합니다. ").append(email).append("\n");

        if (animals.isEmpty()) {

        } else {
            notificationMessage.append("입력하신 정보의 동물이 있습니다. \n");
            animals.forEach(animal -> notificationMessage.append(animal.toString()).append("\n"));
        }

        return notificationMessage.toString();
    }
}