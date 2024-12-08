// Animal 클래스는 데이터베이스에서 가져온 유기동물 데이터를 공통 속성으로 관리하는 추상 클래스
package org.example.puppyhome.animalfactory;

import java.util.Arrays;
import java.util.List;

public abstract class Animal {
    protected String id;
    protected String announcementPeriod; // 공고기간
    protected String announcementNumber; // 공고번호
    protected String intakeAge; // 입소 당시 나이
    protected String weight; // 몸무게
    protected String breed; // 품종
    protected String managementNumber; // 관리번호
    protected String type; // 종류
    protected String gender; // 성별
    protected String foundLocation; // 발견장소
    protected String regionClassification; // 지역분류
    protected String furColor; // 모색
    protected String adoptionStatus; // 입양상태
    protected String adoptionStartDate; // 입양 신청 시작일시
    protected String additionalInfo; // 기타정보
    protected String rescueDate; // 구조일
    protected List<String> imageUrls; // 이미지 URL 목록

    public Animal(String id, String announcementPeriod, String announcementNumber, String intakeAge, String weight, String breed,
                  String managementNumber, String type, String gender, String foundLocation, String regionClassification,
                  String furColor, String adoptionStatus, String adoptionStartDate, String additionalInfo,
                  String rescueDate, List<String> imageUrls) {
        this.id = id;
        this.announcementPeriod = announcementPeriod;
        this.announcementNumber = announcementNumber;
        this.intakeAge = intakeAge;
        this.weight = weight;
        this.breed = breed;
        this.managementNumber = managementNumber;
        this.type = type;
        this.gender = gender;
        this.foundLocation = foundLocation;
        this.regionClassification = regionClassification;
        this.furColor = furColor;
        this.adoptionStatus = adoptionStatus;
        this.adoptionStartDate = adoptionStartDate;
        this.additionalInfo = additionalInfo;
        this.rescueDate = rescueDate;
        this.imageUrls = imageUrls;
    }

    // 유기동물 정보를 출력하는 추상 메서드
    public abstract void displayInfo();

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

    // Setter에서 문자열 데이터를 리스트로 변환
    public void setImageUrls(String imageUrls) {
        // 문자열을 쉼표(,)로 분리하여 리스트로 변환
        this.imageUrls = Arrays.asList(imageUrls.split(",\\s*"));
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }
}
