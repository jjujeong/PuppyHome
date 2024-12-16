package org.example.puppyhome.notification;

import org.example.puppyhome.dbstorage.model.Animal;

import java.util.List;

public class PhoneSubscriber implements Subscriber {
    private String phoneNum;

    public PhoneSubscriber(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String getIdentifier() {
        return phoneNum;
    }

    @Override
    public String notify(List<Animal> animals) {
        StringBuilder notificationMessage = new StringBuilder();
        notificationMessage.append("문자로 데이터를 전송합니다. ").append(phoneNum).append("\n");

        if (animals.isEmpty()) {
            notificationMessage.append("해당하는 동물이 없습니다.\n");
        } else {
            notificationMessage.append("입력하신 정보의 동물이 있습니다. \n");
            animals.forEach(animal -> notificationMessage.append(animal.toString()).append("\n"));
        }

        return notificationMessage.toString();
    }
}