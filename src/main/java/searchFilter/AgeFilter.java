package searchFilter;

import dbstorage.model.Animal;

public class AgeFilter extends SearchFilter {
    private int minAge;
    private int maxAge;

    public AgeFilter(int minAge, int maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    @Override
    protected boolean matches(Animal animal) {
        int age = parseAge(animal.getIntakeAge());
        return age >= minAge && age <= maxAge;
    }

    private int parseAge(String ageString) {
        // "2 months", "3 years" 같은 문자열 처리
        if (ageString.contains("month")) {
            return Integer.parseInt(ageString.split(" ")[0]);
        } else if (ageString.contains("year")) {
            return Integer.parseInt(ageString.split(" ")[0]) * 12;
        }
        return 0;
    }
}
