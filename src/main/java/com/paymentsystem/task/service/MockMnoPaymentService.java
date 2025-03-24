package com.paymentsystem.task.service;


import org.springframework.stereotype.Service;

@Service
public class MockMnoPaymentService {

    public boolean processPayment(String phoneNumber, double amount) {
        // Simulate payment processing logic
        System.out.println("Processing payment of " + amount + " to " + phoneNumber);
        return true;
    }
}
