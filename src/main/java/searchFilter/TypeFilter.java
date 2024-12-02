package searchFilter;

import dbstorage.model.Animal;

public class TypeFilter extends SearchFilter {
    private String type;

    public TypeFilter(String type) {
        this.type = type;
    }

    @Override
    protected boolean matches(Animal animal) {
        return animal.getType().equalsIgnoreCase(type);
    }
}
