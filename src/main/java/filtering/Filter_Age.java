
package filtering;

import dbstorage.model.Animal;
import dbstorage.repository.AnimalRepository;

public class Filter_Age extends Abstract_Filter {

    private final double startAge;
    private final double endAge;

    public Filter_Age(double startAge, double endAge, AnimalRepository animalRepository) { // 6개월 ~ 12개월 연령 검색
        super(animalRepository);
        this.startAge = startAge;
        this.endAge = endAge;
    }

    public Filter_Age(double age, AnimalRepository animalRepository) { // ex) 6개월 연령 검색
        super(animalRepository);
        this.startAge = age;
        this.endAge = age;
    }


    @Override
    protected boolean filter(Animal animal) {
        double intakeAge = parseAge(animal.getIntakeAge());
        return intakeAge >= startAge && intakeAge <= endAge;
    }

    private double parseAge(String ageString) {
        double age = 0.0;
        try {
            if (ageString.contains("(추정)")) {
                String filterAgeString = ageString.split("(추정)")[0].trim();
                if (filterAgeString.contains("년령")) {
                    String yearPart = filterAgeString.split("년령")[0].trim();
                    age = Double.parseDouble(yearPart) * 12;
                }
                else if (filterAgeString.contains("개월령")) {
                    String monthPart = filterAgeString.split("개월령")[0].trim();
                    age = Double.parseDouble(monthPart);
                }
            } else {
                if (ageString.contains("년령")) {
                    String yearPart = ageString.split("년령")[0].trim();
                    age = Double.parseDouble(yearPart) * 12;
                }
                else if (ageString.contains("개월령")) {
                    String monthPart = ageString.split("개월령")[0].trim();
                    age = Double.parseDouble(monthPart);
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("잘못된 나이 형식입니다: " + ageString);
        }
        return age;
    }
}
