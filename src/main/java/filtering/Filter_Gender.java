package filtering;

import dbstorage.model.Animal;
import dbstorage.repository.AnimalRepository;

public class Filter_Gender extends Abstract_Filter {

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
