package filtering;

import dbstorage.model.Animal;
import dbstorage.repository.AnimalRepository;

public class GenderSearch extends AnimalSearchTemplate {

    private final String gender;

    public GenderSearch(String gender, AnimalRepository animalRepository) {
        super(animalRepository);
        this.gender = gender;
    }

    @Override
    protected boolean filter(Animal animal)
    {
        return animal.getGender().equalsIgnoreCase(gender);
    }
}
