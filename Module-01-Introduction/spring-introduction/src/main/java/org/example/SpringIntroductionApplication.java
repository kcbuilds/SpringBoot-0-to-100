package org.example;

import org.example.beans.PaymentService;
import org.example.di.EmailNotification;
import org.example.di.NotificationService;
import org.example.di.SmsNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIntroductionApplication implements CommandLineRunner {

//    @Autowired
//    PaymentService paymentService;

    @Autowired
    NotificationService notificationService;  // Field DI


    public static void main(String[] args) {
        SpringApplication.run(SpringIntroductionApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
//        paymentService.payment(2000.0);

        notificationService.sendNotification("Sure");
    }
}