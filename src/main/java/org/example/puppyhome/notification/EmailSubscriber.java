package org.example.puppyhome.notification;

import java.util.List;
import org.example.puppyhome.dbstorage.model.Animal;

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
}
