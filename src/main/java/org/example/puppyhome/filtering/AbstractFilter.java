package org.example.puppyhome.filtering;

import org.example.puppyhome.dbstorage.model.Animal;
import org.example.puppyhome.dbstorage.repository.AnimalRepository;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class AbstractFilter {

    private final AnimalRepository animalRepository;

    public AbstractFilter(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    // 템플릿 메서드: 검색 로직의 골격 정의
    public final List<Animal> search() {
        List<Animal> allAnimals = animalRepository.findAll();
        return allAnimals.stream()
                .filter(this::filter) // 조건에 맞는 동물 필터링
                .collect(Collectors.toList());
    }

    // 추상 메서드: 각 필터 조건 정의
    protected abstract boolean filter(Animal animal);
}