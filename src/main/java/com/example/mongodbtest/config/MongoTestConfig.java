package com.example.mongodbtest.config;

import com.example.mongodbtest.model.TestEntity;
import com.example.mongodbtest.repository.TestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoTestConfig {

    @Bean
    CommandLineRunner testMongoConnection(TestRepository testRepository) {
        return args -> {
            // 저장
            testRepository.save(new TestEntity("Hello MongoDB"));

            // 조회
            testRepository.findAll().forEach(entity ->
                    System.out.println("Found entity: " + entity.getName())
            );
        };
    }
}
