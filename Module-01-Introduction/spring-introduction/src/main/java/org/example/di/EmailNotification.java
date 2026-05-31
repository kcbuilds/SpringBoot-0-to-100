package org.example.di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class EmailNotification implements NotificationService{

    @Override
    public void sendNotification(String message) {
        System.out.println("Email: " + message);
    }
}
