package org.example.puppyhome.notification;

public class Main {
    public static void main(String[] args) {
        CrawlingDetector jinju = new CrawlingDetector();
        CrawlingDetector hyunjeo = new CrawlingDetector();

        jinju.subscribe("likeData", new AppSubscriber("jinju", "20221992"));
        hyunjeo.subscribe("likeData", new EmailSubscriber("hyunjeo", "20222039@edu.hanbat.ac.kr"));

        jinju.notify("likeData", "조건에 맞는 동물 정보가 등록되었습니다.");
        hyunjeo.notify("allData", "");
    }
}
