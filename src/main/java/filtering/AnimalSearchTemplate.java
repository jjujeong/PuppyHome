package filtering;

import dbstorage.model.Animal;
import dbstorage.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class AnimalSearchTemplate {

    private final AnimalRepository animalRepository;

    public AnimalSearchTemplate(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    // 템플릿 메서드: 검색 로직의 뼈대
    public final List<Animal> search() {
        List<Animal> allAnimals = animalRepository.findAll();
        return allAnimals.stream()
                .filter(this::filter) // 조건에 맞는 동물 필터링
                .collect(Collectors.toList());
    }

    // 필터 조건을 정의하는 추상 메서드
    protected abstract boolean filter(Animal animal);
}
