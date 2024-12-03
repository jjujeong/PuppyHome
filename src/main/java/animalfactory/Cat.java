package animalfactory;

import java.util.List;

public class Cat extends Animal {
    public Cat(String id, String announcementPeriod, String announcementNumber, String intakeAge, String weight, String breed,
               String managementNumber, String type, String gender, String foundLocation, String regionClassification,
               String furColor, String adoptionStatus, String adoptionStartDate, String additionalInfo,
               String rescueDate, List<String> imageUrls) {
        super(id, announcementPeriod, announcementNumber, intakeAge, weight, breed, managementNumber, type, gender,
                foundLocation, regionClassification, furColor, adoptionStatus, adoptionStartDate, additionalInfo,
                rescueDate, imageUrls);
    }

    @Override
    public void displayInfo() {
        System.out.println("Cat - Breed: " + breed + ", Gender: " + gender + ", Weight: " + weight);
    }
}
