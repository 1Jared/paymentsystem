package com.paymentsystem.task.service;

import org.springframework.stereotype.Service;

@Service
public class MockSmsGatewayService implements SmsNotificationService {

    @Override
    public void sendSms(String phoneNumber, String message) {
        // Simulate sending SMS
        System.out.println("Mock SMS sent to " + phoneNumber + ": " + message);
    }
}
