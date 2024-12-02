package filtering;

import dbstorage.model.Animal;
import dbstorage.repository.AnimalRepository;

public class Filter_Breed extends Abstract_Filter {

    private final String type;

    public Filter_Breed(String type, AnimalRepository animalRepository) {
        super(animalRepository);
        this.type = type;
    }

    @Override
    protected boolean filter(Animal animal) {
        return animal.getBreed().equalsIgnoreCase(type);
    }
}

