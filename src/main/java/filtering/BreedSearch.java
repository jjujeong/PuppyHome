package filtering;

import dbstorage.model.Animal;
import dbstorage.repository.AnimalRepository;

public class BreedSearch extends AnimalSearchTemplate {

    private final String type;

    public BreedSearch(String type, AnimalRepository animalRepository) {
        super(animalRepository);
        this.type = type;
    }

    @Override
    protected boolean filter(Animal animal) {
        return animal.getBreed().equalsIgnoreCase(type);
    }
}

