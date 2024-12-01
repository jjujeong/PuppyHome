// 데이터베이스에서 데이터를 가져와 적절한 객체를 생성하는 Factory 클래스
package animalfactory;

import java.util.List;

public class AnimalFactory {
    public static Animal createAnimal(String id, String announcementPeriod, String announcementNumber,
                                      String intakeAge, String weight, String breed, String managementNumber, String type,
                                      String gender, String foundLocation, String regionClassification,
                                      String furColor, String adoptionStatus, String adoptionStartDate,
                                      String additionalInfo, String rescueDate, List<String> imageUrls) {
        switch (type.toLowerCase()) {
            case "개":
                return new Dog(id, announcementPeriod, announcementNumber, intakeAge, weight, breed, managementNumber,
                        type, gender, foundLocation, regionClassification, furColor, adoptionStatus, adoptionStartDate,
                        additionalInfo, rescueDate, imageUrls);
            case "고양이":
                return new Cat(id, announcementPeriod, announcementNumber, intakeAge, weight, breed, managementNumber,
                        type, gender, foundLocation, regionClassification, furColor, adoptionStatus, adoptionStartDate,
                        additionalInfo, rescueDate, imageUrls);
            default:
                return new Ect(id, announcementPeriod, announcementNumber, intakeAge, weight, breed, managementNumber,
                        type, gender, foundLocation, regionClassification, furColor, adoptionStatus, adoptionStartDate,
                        additionalInfo, rescueDate, imageUrls);
        }
    }
}
