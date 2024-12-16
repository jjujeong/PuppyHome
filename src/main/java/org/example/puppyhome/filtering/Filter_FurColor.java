package org.example.puppyhome.filtering;

import org.example.puppyhome.dbstorage.model.Animal;
import org.example.puppyhome.dbstorage.repository.AnimalRepository;


public class Filter_FurColor extends AbstractFilter {

    private final String type;

    public Filter_FurColor(String type, AnimalRepository animalRepository) {
        super(animalRepository);
        this.type = type;
    }

    @Override
    protected boolean filter(Animal animal) {
        return animal.getFurColor().equalsIgnoreCase(type);
    }
}

