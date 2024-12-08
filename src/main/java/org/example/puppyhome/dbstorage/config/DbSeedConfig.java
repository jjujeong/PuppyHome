package org.example.puppyhome.dbstorage.config;

import org.example.puppyhome.dbstorage.model.Animal;
import org.example.puppyhome.dbstorage.repository.AnimalRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.InputStream;
import java.util.List;

@Configuration
@Profile("dbstorage")
public class DbSeedConfig {

    @Bean
    CommandLineRunner seedMongoData(AnimalRepository animalRepository) {
        return args -> {
            // JSON 파일 읽기
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream("/converted_animal_data.json");

            List<Animal> animals = objectMapper.readValue(inputStream, new TypeReference<>() {});

            // MongoDB에 저장
            animalRepository.saveAll(animals);

            System.out.println("저장 완료");
        };
    }
}
