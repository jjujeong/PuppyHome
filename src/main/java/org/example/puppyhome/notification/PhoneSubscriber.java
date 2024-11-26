package org.example.puppyhome.notification;

public class PhoneSubscriber implements Subscriber {
    private String name;
    private String phoneNumber;

    public PhoneSubscriber(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(String data) {
        System.out.println( name + "님에게 " + phoneNumber + "(으)로 " + data + "(을)를 전송했습니다.");
        // 실제 SMS 전송 로직 추가 가능
    }
}
