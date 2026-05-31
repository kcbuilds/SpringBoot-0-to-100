package org.example.di;

import org.springframework.stereotype.Component;

@Component
public class SmsNotification implements NotificationService {

    @Override
    public void sendNotification(String message) {
        System.out.println("Sms: " +message);
    }
}
