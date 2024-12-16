package org.example.puppyhome.filtering;

import org.example.puppyhome.dbstorage.model.Animal;
import org.example.puppyhome.dbstorage.repository.AnimalRepository;

public class Filter_Type extends AbstractFilter {

    private final String type;

    public Filter_Type(String type, AnimalRepository animalRepository) {
        super(animalRepository);
        this.type = type;
    }

    @Override
    protected boolean filter(Animal animal) {
        return animal.getType().equalsIgnoreCase(type);
    }
}

