package org.example.puppyhome.notification;

import java.util.List;
import org.example.puppyhome.dbstorage.model.Animal;

public class AppSubscriber implements Subscriber {
    private final String appId;

    public AppSubscriber(String appId) {
        this.appId = appId;
    }

    @Override
    public void notify(List<Animal> animals) {
        System.out.println("Sending app to: " + appId);
        if (animals.isEmpty()) {
            System.out.println("No animals matching your criteria.");
        } else {
            System.out.println("Matching animals: ");
            animals.forEach(System.out::println);
        }
    }
}
