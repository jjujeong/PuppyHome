package org.example.puppyhome.notification;

import java.util.List;
import dbstorage.model.Animal;

public class EmailSubscriber implements Subscriber {
    private final String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void notify(List<Animal> animals) {
        System.out.println("email로 알림을 전송합니다. " + email);
        if (animals.isEmpty()) {
            System.out.println("조건에 맞는 동물이 없습니다..");
        } else {
            System.out.println("조건에 맞는 동물들 :  ");
            animals.forEach(System.out::println);
        }
    }
//    System.out.println( subscriber.getClass().getSimpleName() + " 에게  " + filteredAnimals.size() + " 동물들.");
//
//            if (subscriber instanceof EmailSubscriber) {
//        System.out.println("설정한 조건에 맞는 동물을 찾았습니다:");
//
//        filteredAnimals.forEach(animal -> {
//            System.out.println(animal.getAnnouncementNumber() + ", " + animal.getImageUrls());
//        });
//    }
}
