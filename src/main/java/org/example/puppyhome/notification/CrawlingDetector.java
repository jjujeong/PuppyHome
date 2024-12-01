package org.example.puppyhome.notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrawlingDetector {
    private final Map<String, List<Subscriber>> listeners = new HashMap<>();

    public void crawlingDetected(Subscriber subscriber) {

    }

    public void subscribe(String eventType, Subscriber listener) {
        listeners.putIfAbsent(eventType, new ArrayList<>());
        listeners.get(eventType).add(listener);
    }

    public void unsubscribe(String eventType, Subscriber listener) {
        List<Subscriber> users = listeners.get(eventType);
        if (users != null) {
            users.remove(listener);
        }
    }

    public void notify(String eventType, String data) {
        List<Subscriber> users = listeners.get(eventType);
        if (users != null) {
            for (Subscriber listener : users) {
                listener.update(data);
            }
        }
    }
}