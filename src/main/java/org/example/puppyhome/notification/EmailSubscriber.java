package org.example.puppyhome.notification;

public class EmailSubscriber implements Subscriber {
    private String name;
    private String email;

    public EmailSubscriber(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public void update(String data) {
        System.out.println( name + "님에게 " + email + "(으)로 " + data + "(을)를 전송했습니다.");
        // 실제 이메일 전송 로직 추가 가능
    }
}
