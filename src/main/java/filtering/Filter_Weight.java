package filtering;

import dbstorage.model.Animal;
import dbstorage.repository.AnimalRepository;

public class Filter_Weight extends Abstract_Filter {

    private final double startWeight;
    private final double endWeight;

    // ex) 1kg ~ 1.5kg 검색
    public Filter_Weight(double startWeight, double endWeight, AnimalRepository animalRepository) {
        super(animalRepository);
        this.startWeight = startWeight;
        this.endWeight = endWeight;
    }

    // ex) 1kg 검색
    public Filter_Weight(double weight, AnimalRepository animalRepository) {
        super(animalRepository);
        this.startWeight = weight;
        this.endWeight = weight;
    }


    @Override
    protected boolean filter(Animal animal) {
        double weight = parseWeight(animal.getWeight());
        return weight >= startWeight && weight <= endWeight;
    }

    private double parseWeight(String weightString) {
        double weight = 0.0;
        try {
            // "(추정)"이 포함된 경우 제거
            if (weightString.contains("(추정)")) {
                weightString = weightString.replace("(추정)", "").trim();
            }
            // "kg" 단위 제거
            if (weightString.contains("kg")) {
                weightString = weightString.replace("kg", "").trim();
            }
            weight = Double.parseDouble(weightString);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 몸무게 형식입니다: " + weightString);
        }
        return weight;
    }

}
