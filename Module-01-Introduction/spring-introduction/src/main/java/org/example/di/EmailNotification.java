package org.example.di;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
@Component
//@Qualifier("emailNotification")
//@ConditionalOnProperty(
//        name = "notification.type",
//        havingValue = "email")
public class EmailNotification implements NotificationService{

    @Override
    public void sendNotification(String message) {
        System.out.println("Email Sending: " + message);
    }
}
