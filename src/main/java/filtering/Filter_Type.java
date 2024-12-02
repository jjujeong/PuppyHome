package filtering;

import dbstorage.model.Animal;
import dbstorage.repository.AnimalRepository;

public class Filter_Type extends Abstract_Filter {

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

