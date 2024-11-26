package org.example.puppyhome.notification;

public class AppSubscriber implements Subscriber {
    private String name;
    private String appId;

    public AppSubscriber(String name, String appId) {
        this.name = name;
        this.appId = appId;
    }

    @Override
    public void update(String data) {
        System.out.println(name + "님에게 " + appId + "(으)로 " + data + "(을)를 전송했습니다.");
        // 실제 푸시 알림 로직 추가 가능
    }
}