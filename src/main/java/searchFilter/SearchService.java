package searchFilter;

import dbstorage.model.Animal;
import dbstorage.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> search(List<SearchFilter> filters) {
        List<Animal> allAnimals = animalRepository.findAll();
        List<Animal> filteredAnimals = new ArrayList<>();

        for (Animal animal : allAnimals) {
            boolean matches = true;
            for (SearchFilter filter : filters) {
                if (!filter.filter(animal)) {
                    matches = false;
                    break;
                }
            }
            if (matches) {
                filteredAnimals.add(animal);
            }
        }
        return filteredAnimals;
    }

    public void displayResults(List<Animal> animals) {
        if (animals.isEmpty()) {
            System.out.println("검색 조건에 맞는 동물이 없습니다.");
        } else {
            for (Animal animal : animals) {
                System.out.println("종류: " + animal.getType() +
                        ", 성별: " + animal.getGender() +
                        ", 나이: " + animal.getIntakeAge() +
                        ", 품종: " + animal.getBreed() +
                        ", 몸무게: " + animal.getWeight());
            }
        }
    }
}
