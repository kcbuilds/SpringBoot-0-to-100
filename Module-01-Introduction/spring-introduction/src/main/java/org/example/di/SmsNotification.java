package org.example.di;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("smsNotification")
//@ConditionalOnProperty(name = "notification.type", havingValue = "sms")
public class SmsNotification implements NotificationService {

    @Override
    public void sendNotification(String message) {
        System.out.println("Sms Sending: " +message);
    }
}
