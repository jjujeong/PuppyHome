package org.example.puppyhome.notification;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.puppyhome.dbstorage.model.Animal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlarmManager {
    private final List<Subscriber> subscribers = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
        System.out.println("Subscriber added: " + subscriber.getClass().getSimpleName());
    }

    public String notifyAlarmOn(AnimalFilter filter) {
        String jsonFilePath = "converted_animal_data.json";
        File jsonFile = new File(jsonFilePath);
        StringBuilder notificationResults = new StringBuilder();

        if (!jsonFile.exists()) {
            notificationResults.append("JSON 파일을 찾을 수 없습니다: ").append(jsonFilePath).append("\n");
            return notificationResults.toString();
        }

        List<Animal> animals = readAnimalsFromJson(jsonFilePath);
        if (animals.isEmpty()) {
            notificationResults.append("json 파일에 동물이 없거나 json 파일이 없습니다.\n");
            return notificationResults.toString();
        }

        List<Animal> filteredAnimals = filterAnimals(animals, filter);

        if (filteredAnimals.isEmpty()) {
            notificationResults.append("필터 기준에 맞는 동물이 없습니다.\n");
        } else {
            notificationResults.append(filteredAnimals.size()).append("건의 알림이 있습니다.\n");
        }

        subscribers.forEach(subscriber -> {
            notificationResults.append(subscriber.notify(filteredAnimals)).append("\n");
        });

        return notificationResults.toString();
    }

    private List<Animal> readAnimalsFromJson(String jsonFilePath) {
        File jsonFile = new File(jsonFilePath);
        if (!jsonFile.exists()) {
            System.err.println("Error: JSON 파일이 없습니다:  " + jsonFilePath);
            return new ArrayList<>();
        }
        System.out.println("json 파일 읽는 중: " + jsonFilePath);
        try {
            return objectMapper.readValue(jsonFile, new TypeReference<List<Animal>>() {});
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private List<Animal> filterAnimals(List<Animal> animals, AnimalFilter filter) {
        return animals.stream()
                .filter(filter::matches)
                .collect(Collectors.toList());
    }
}