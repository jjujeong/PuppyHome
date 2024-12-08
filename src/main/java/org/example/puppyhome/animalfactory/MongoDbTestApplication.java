package org.example.puppyhome.animalfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//@SpringBootApplication(scanBasePackages = {"animalfactory", "dbstorage"})
@SpringBootApplication(scanBasePackages = {"org.example.puppyhome"})
public class MongoDbTestApplication {
    public static void main(String[] args) {
        // Spring Boot 실행
        ConfigurableApplicationContext context = SpringApplication.run(MongoDbTestApplication.class, args);

        // AnimalService 호출
        AnimalService animalService = context.getBean(AnimalService.class);
        animalService.processAnimals();
    }
}
