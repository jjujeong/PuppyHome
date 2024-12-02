package filtering;

import dbstorage.model.Animal;
import dbstorage.repository.AnimalRepository;

public class TypeSearch extends AnimalSearchTemplate {

    private final String type;

    public TypeSearch(String type, AnimalRepository animalRepository) {
        super(animalRepository);
        this.type = type;
    }

    @Override
    protected boolean filter(Animal animal) {
        return animal.getType().equalsIgnoreCase(type);
    }
}

