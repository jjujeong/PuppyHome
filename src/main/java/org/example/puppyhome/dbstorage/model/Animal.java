
package org.example.puppyhome.dbstorage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Document(collection = "animals")
public class Animal {
    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("공고기간")
    private String announcementPeriod;

    @JsonProperty("공고번호")
    private String announcementNumber;

    @JsonProperty("입소 당시 나이")
    private String intakeAge;

    @JsonProperty("몸무게")
    private String weight;

    @JsonProperty("품종")
    private String breed;

    @JsonProperty("관리번호")
    private String managementNumber;

    @JsonProperty("종류")
    private String type;

    @JsonProperty("성별")
    private String gender;

    @JsonProperty("발견장소")
    private String foundLocation;

    @JsonProperty("지역분류")
    private String regionClassification;

    @JsonProperty("모색")
    private String furColor;

    @JsonProperty("입양상태")
    private String adoptionStatus;

    @JsonProperty("입양신청 시작 일시")
    private String adoptionStartDate;

    @JsonProperty("기타정보")
    private String additionalInfo;

    @JsonProperty("구조일")
    private String rescueDate;

    @JsonProperty("imageUrls")
    private List<String> imageUrls;


    // Getter, Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnnouncementPeriod() {
        return announcementPeriod;
    }

    public void setAnnouncementPeriod(String announcementPeriod) {
        this.announcementPeriod = announcementPeriod;
    }

    public String getAnnouncementNumber() {
        return announcementNumber;
    }

    public void setAnnouncementNumber(String announcementNumber) {
        this.announcementNumber = announcementNumber;
    }

    public String getIntakeAge() {
        return intakeAge;
    }

    public void setIntakeAge(String intakeAge) {
        this.intakeAge = intakeAge;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getManagementNumber() {
        return managementNumber;
    }

    public void setManagementNumber(String managementNumber) {
        this.managementNumber = managementNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFoundLocation() {
        return foundLocation;
    }

    public void setFoundLocation(String foundLocation) {
        this.foundLocation = foundLocation;
    }

    public String getRegionClassification() {
        return regionClassification;
    }

    public void setRegionClassification(String regionClassification) {
        this.regionClassification = regionClassification;
    }

    public String getFurColor() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }

    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public String getAdoptionStartDate() {
        return adoptionStartDate;
    }

    public void setAdoptionStartDate(String adoptionStartDate) {
        this.adoptionStartDate = adoptionStartDate;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getRescueDate() {
        return rescueDate;
    }

    public void setRescueDate(String rescueDate) {
        this.rescueDate = rescueDate;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = Arrays.asList(imageUrls.split(",\\s*"));
    }

    @Override
    public String toString() {
        return  "공고번호 : " + announcementNumber + ", 이미지 : " + imageUrls ;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }
}
