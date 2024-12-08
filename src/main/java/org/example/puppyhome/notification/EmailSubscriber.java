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
        notificationMessage.append("Sending app to: ").append(email).append("\n");

        if (animals.isEmpty()) {
            notificationMessage.append("No animals matching your criteria.\n");
        } else {
            notificationMessage.append("Matching animals: \n");
            animals.forEach(animal -> notificationMessage.append(animal.toString()).append("\n"));
        }

        return notificationMessage.toString();
    }
}