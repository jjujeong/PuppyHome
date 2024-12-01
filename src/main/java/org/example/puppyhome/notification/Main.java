package org.example.puppyhome.notification;

public class Main {
    public static void main(String[] args) {
        // 동물 조건 알림 서비스를 이용할 사용자
        AlarmManager hyunjeon = new AlarmManager();
        AlarmManager jinju = new AlarmManager();
        AlarmManager hyeonseo = new AlarmManager();

        // 사용자가 원하는 구독 방식 설정
        hyunjeon.subscribe(new PhoneSubscriber( "010-5062-7450"));
        jinju.subscribe(new EmailSubscriber( "20221992@edu.hanbat.ac.kr"));
        hyeonseo.subscribe(new AppSubscriber( "hyunseo0822"));


        // 사용자가 원하는 조건 설정
        AnimalFilter hyunjeonFilter = new AnimalFilter()
                .setType("개")
                .setIntakeAge("3년령", "10년령");

        AnimalFilter jinjuFilter = new AnimalFilter()
                .setType("고양이");

        AnimalFilter hyeonseoFilter = new AnimalFilter()
                .setType("개")
                .setBreed("믹스");



//        // 조건에 따른 알림 설정
//        hyunjeon.notifySubscribers(hyunjeonFilter);
//        jinju.notifySubscribers(jinjuFilter);
        hyeonseo.notifyAlarmOn(hyeonseoFilter);


    }
}