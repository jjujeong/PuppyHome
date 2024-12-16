package org.example.puppyhome.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.puppyhome.dbstorage.model.Animal;
import jakarta.persistence.Embeddable;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
class AnimalFilter {
    private String breed;
    private String type;
    private String gender;
    private String regionClassification;
    private String adoptionStatus;
    private String furColor;
    private String announcementPeriod;
    private String announcementNumber;
    private String intakeAge;
    private String weight;
    private String foundLocation;
    private String additionalInfo;
    private String rescueDate;
    private double startAge;
    private double endAge;

    // 각 필드에 대한 Setter 메서드
    public AnimalFilter setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public AnimalFilter setType(String type) {
        this.type = type;
        return this;
    }

    public AnimalFilter setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public AnimalFilter setRegionClassification(String regionClassification) {
        this.regionClassification = regionClassification;
        return this;
    }

    public AnimalFilter setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
        return this;
    }

    public AnimalFilter setFurColor(String furColor) {
        this.furColor = furColor;
        return this;
    }

    public AnimalFilter setAnnouncementNumber(String announcementNumber) {
        this.announcementNumber = announcementNumber;
        return this;
    }

    public AnimalFilter setIntakeAge(String age) {
        if (age == null || age.isEmpty()) {
            this.startAge = 0.0;
            this.endAge = 0.0;
        } else {
            if (age.contains(",")) {
                String[] ages = age.split(",");
                this.startAge = parseAge(ages[0].trim());
                this.endAge = (ages.length > 1) ? parseAge(ages[1].trim()) : this.startAge;
            } else {
                this.startAge = parseAge(age);
                this.endAge = this.startAge;
            }
        }
        return this;
    }

    private double parseAge(String ageString) {
        if (ageString == null || ageString.isEmpty()) return 0.0;
        double age = 0.0;
        try {
            if (ageString.contains("(추정)")) {
                String filterAgeString = ageString.split("(추정)")[0].trim();
                transformAge(filterAgeString);
            } else {
                transformAge(ageString);
            }

        } catch (NumberFormatException e) {
            System.out.println("잘못된 나이 형식입니다: " + ageString);
        }
        return age;
    }

    private double transformAge(String ageString) {
        double age = 0.0;

        if (ageString.contains("년령")) {
            String yearPart = ageString.split("년령")[0].trim();
            age = Double.parseDouble(yearPart) * 12;
        }
        else if (ageString.contains("개월령")) {
            String monthPart = ageString.split("개월")[0].trim();
            age = Double.parseDouble(monthPart);
        }
        return age;
    }

    public AnimalFilter setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public AnimalFilter setFoundLocation(String foundLocation) {
        this.foundLocation = foundLocation;
        return this;
    }

    public AnimalFilter setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public AnimalFilter setRescueDate(String rescueDate) {
        this.rescueDate = rescueDate;
        return this;
    }

    public boolean matches(Animal animal) {
        if (animal == null) {
            return false;
        }

        return (breed == null || animal.getBreed().equalsIgnoreCase(breed)) &&
                (type == null || animal.getType().equalsIgnoreCase(type)) &&
                (gender == null || animal.getGender().equalsIgnoreCase(gender)) &&
                (regionClassification == null || animal.getRegionClassification().equalsIgnoreCase(regionClassification)) &&
                (adoptionStatus == null || animal.getAdoptionStatus().equalsIgnoreCase(adoptionStatus)) &&
                (furColor == null || animal.getFurColor().equalsIgnoreCase(furColor)) &&
                (announcementPeriod == null || animal.getAnnouncementPeriod().equalsIgnoreCase(announcementPeriod)) &&
                (announcementNumber == null || animal.getAnnouncementNumber().equalsIgnoreCase(announcementNumber)) &&
                (weight == null || animal.getWeight().equalsIgnoreCase(weight)) &&
                (foundLocation == null || animal.getFoundLocation().equalsIgnoreCase(foundLocation)) &&
                (additionalInfo == null || animal.getAdditionalInfo().contains(additionalInfo)) && // 포함 여부로 체크
                (rescueDate == null || animal.getRescueDate().equalsIgnoreCase(rescueDate)) &&
                matchesIntakeAge(animal);
    }

    private boolean matchesIntakeAge(Animal animal) {
        if (startAge == 0 && endAge == 0) {
            return true;
        }

        String intakeAgeStr = animal.getIntakeAge();
        double intakeAge = parseAge(intakeAgeStr);

        return intakeAge >= startAge & intakeAge <= endAge;
    }
}