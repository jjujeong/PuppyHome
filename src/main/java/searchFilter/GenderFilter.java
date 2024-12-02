package searchFilter;

import dbstorage.model.Animal;

public class GenderFilter extends SearchFilter {
    private String gender;

    public GenderFilter(String gender) {
        this.gender = gender;
    }

    @Override
    protected boolean matches(Animal animal) {
        return animal.getGender().equalsIgnoreCase(gender);
    }
}
