package org.example.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    public void payment(double price) {
        System.out.println("Payment Paid : " + price);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("PostConstruct Called");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("PreDestroy called");
    }


}
