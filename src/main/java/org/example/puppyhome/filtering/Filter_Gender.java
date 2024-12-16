package org.example.puppyhome.filtering;

import org.example.puppyhome.dbstorage.model.Animal;
import org.example.puppyhome.dbstorage.repository.AnimalRepository;


public class Filter_Gender extends AbstractFilter {

    private final String gender;

    public Filter_Gender(String gender, AnimalRepository animalRepository) {
        super(animalRepository);
        this.gender = gender;
    }

    @Override
    protected boolean filter(Animal animal)
    {
        return animal.getGender().equalsIgnoreCase(gender);
    }
}
