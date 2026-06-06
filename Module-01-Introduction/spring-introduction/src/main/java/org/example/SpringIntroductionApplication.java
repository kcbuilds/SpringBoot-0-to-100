package org.example;

import org.example.beans.PaymentService;
import org.example.di.EmailNotification;
import org.example.di.NotificationService;
import org.example.di.SmsNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringIntroductionApplication implements CommandLineRunner {

//    @Autowired
//    PaymentService paymentService;

//    @Autowired
//    NotificationService notificationService;  // Field DI
//
//    public SpringIntroductionApplication(
//            @Qualifier("smsNotification")
//            NotificationService notificationService) {
//        this.notificationService = notificationService;
//    }

    @Autowired
    Map<String, NotificationService>  notificationServiceMap = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(SpringIntroductionApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
//        paymentService.payment(2000.0);

//        notificationService.sendNotification("Hello World!");

        for (var notificationService : notificationServiceMap.entrySet()) {
            System.out.println(notificationService.getKey());
            notificationService.getValue().sendNotification("Hello World!");
        }

    }
}