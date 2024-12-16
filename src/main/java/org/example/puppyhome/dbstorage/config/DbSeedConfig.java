package org.example.puppyhome.dbstorage.config;

import org.example.puppyhome.dbstorage.model.Animal;
import org.example.puppyhome.dbstorage.repository.AnimalRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@Profile("dbstorage")
public class DbSeedConfig {

    @Bean
    public CommandLineRunner seedMongoData(AnimalRepository animalRepository) {
        return args -> {

            // MongoDB에서 모든 announcementNumber 가져오기
            List<Animal> existingAnimals = animalRepository.findAll();
            Set<String> existingAnnouncementNumbers = new HashSet<>();
            for (Animal animal : existingAnimals) {
                if (animal.getAnnouncementNumber() != null) {
                    existingAnnouncementNumbers.add(animal.getAnnouncementNumber());
                }
            }

            // JSON 파일 읽기
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(new File("converted_animal_data.json"));

            List<Animal> animals = objectMapper.readValue(inputStream, new TypeReference<>() {});

            // 중복 체크 후 새로운 데이터만 저장
            for (Animal animal : animals) {
                // Set을 사용해 중복 체크
                if (!existingAnnouncementNumbers.contains(animal.getAnnouncementNumber())) {
                    animalRepository.save(animal);
                    existingAnnouncementNumbers.add(animal.getAnnouncementNumber()); // Set에 추가
                }
            }
        };
    }
}