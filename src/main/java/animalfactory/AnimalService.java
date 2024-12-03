// MongoDB에서 데이터를 가져와 Factory를 통해 객체화하는 예제
package animalfactory;

import dbstorage.model.Animal;
import dbstorage.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    // MongoDB에서 데이터를 처리하는 메서드
    public void processAnimals() {
        // MongoDB에서 데이터 가져오기
        List<Animal> dbAnimals = animalRepository.findAll();

        // 각 Animal 객체를 Factory를 통해 생성하고 처리
        for (Animal dbAnimal : dbAnimals) {
            animalfactory.Animal createdAnimal = mapToFactoryAnimal(dbAnimal);
            createdAnimal.displayInfo(); // 동물 정보 출력
        }
    }

    // MongoDB 모델 객체를 Factory 모델 객체로 변환하는 메서드
    private animalfactory.Animal mapToFactoryAnimal(dbstorage.model.Animal dbAnimal) {

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
