//// MongoDB에서 데이터를 가져와 Factory를 통해 객체화하는 예제
//package org.example.puppyhome.animalfactory;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.example.puppyhome.dbstorage.model.Animal;
//import org.example.puppyhome.dbstorage.repository.AnimalRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@Service
//@RestController
//@Tag(name ="Animal Object")
//public class AnimalService {
//
//    @Autowired
//    private AnimalRepository animalRepository;
//
//    // MongoDB에서 데이터를 처리하는 메서드
//    @GetMapping("/process-animals")
//    @Operation(summary = "유기동물 객체 생성", description = "MongoDB에서 유기동물 데이터를 가져와 객체를 생성합니다.")
//    public void processAnimals() {
//        // MongoDB에서 데이터 가져오기
//        List<Animal> dbAnimals = animalRepository.findAll();
//
//        // 각 Animal 객체를 Factory를 통해 생성하고 처리
//        for (Animal dbAnimal : dbAnimals) {
//            org.example.puppyhome.animalfactory.Animal createdAnimal = mapToFactoryAnimal(dbAnimal);
//            createdAnimal.displayInfo(); // 동물 정보 출력
//        }
//    }
//
//    // MongoDB 모델 객체를 Factory 모델 객체로 변환하는 메서드
//    private org.example.puppyhome.animalfactory.Animal mapToFactoryAnimal(Animal dbAnimal) {
//
//        return AnimalFactory.createAnimal(
//                dbAnimal.getId(),
//                dbAnimal.getAnnouncementPeriod(),
//                dbAnimal.getAnnouncementNumber(),
//                dbAnimal.getIntakeAge(),
//                dbAnimal.getWeight(),
//                dbAnimal.getBreed(),
//                dbAnimal.getManagementNumber(),
//                dbAnimal.getType(),
//                dbAnimal.getGender(),
//                dbAnimal.getFoundLocation(),
//                dbAnimal.getRegionClassification(),
//                dbAnimal.getFurColor(),
//                dbAnimal.getAdoptionStatus(),
//                dbAnimal.getAdoptionStartDate(),
//                dbAnimal.getAdditionalInfo(),
//                dbAnimal.getRescueDate(),
//                dbAnimal.getImageUrls()
//        );
//
//    }
//}



package org.example.puppyhome.animalfactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.puppyhome.animalfactory.AnimalFactory;
import org.example.puppyhome.dbstorage.model.Animal;
import org.example.puppyhome.dbstorage.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "유기동물 객체 생성", description = "유기동물 데이터를 처리하는 API")
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    // MongoDB에서 데이터를 처리하는 메서드
    @GetMapping("/process-animals")
    @Operation(summary = "유기동물 객체 생성", description = "MongoDB에서 유기동물 데이터를 가져와 객체를 생성한 후 반환합니다.")
    public List<String> processAnimals() {
        // MongoDB에서 데이터 가져오기
        List<Animal> dbAnimals = animalRepository.findAll();

        // 반환할 동물 정보 리스트
        List<String> results = new ArrayList<>();

        // 각 Animal 객체를 Factory를 통해 생성하고 처리
        for (Animal dbAnimal : dbAnimals) {
            org.example.puppyhome.animalfactory.Animal createdAnimal = mapToFactoryAnimal(dbAnimal);
            String animalInfo = createdAnimal.toString(); // 동물 정보를 문자열로 변환
            createdAnimal.displayInfo(); // 동물 정보 콘솔 출력
            results.add(animalInfo); // 결과 리스트에 추가
        }

        // 결과 리스트 반환
        return results;
    }

    // MongoDB 모델 객체를 Factory 모델 객체로 변환하는 메서드
    private org.example.puppyhome.animalfactory.Animal mapToFactoryAnimal(Animal dbAnimal) {

        return AnimalFactory.createAnimal(
                dbAnimal.getId(),
                dbAnimal.getAnnouncementPeriod(),
                dbAnimal.getAnnouncementNumber(),
                dbAnimal.getIntakeAge(),
                dbAnimal.getWeight(),
                dbAnimal.getBreed(),
                dbAnimal.getManagementNumber(),
                dbAnimal.getType(),
                dbAnimal.getGender(),
                dbAnimal.getFoundLocation(),
                dbAnimal.getRegionClassification(),
                dbAnimal.getFurColor(),
                dbAnimal.getAdoptionStatus(),
                dbAnimal.getAdoptionStartDate(),
                dbAnimal.getAdditionalInfo(),
                dbAnimal.getRescueDate(),
                dbAnimal.getImageUrls()
        );

    }
}

