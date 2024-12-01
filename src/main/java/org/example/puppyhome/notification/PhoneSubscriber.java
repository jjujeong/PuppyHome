package org.example.puppyhome.notification;

import java.util.List;
import dbstorage.model.Animal;

public class PhoneSubscriber implements Subscriber {
    private final String phoneNumber;

    public PhoneSubscriber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void notify(List<Animal> animals) {
        System.out.println("Sending phone to: " + phoneNumber);
        if (animals.isEmpty()) {
            System.out.println("No animals matching your criteria.");
        } else {
            System.out.println("Matching animals: ");
            animals.forEach(System.out::println);
        }
    }
}
