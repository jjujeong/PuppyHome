package org.example.puppyhome.notification;

import org.example.puppyhome.dbstorage.model.Animal;
import java.util.List;

public interface Subscriber {
    void notify(List<Animal> animals); // 기본 구현 없이 선언만
}