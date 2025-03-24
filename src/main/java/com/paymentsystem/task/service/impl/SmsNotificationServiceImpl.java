package com.paymentsystem.task.service.impl;


import com.paymentsystem.task.service.SmsNotificationService;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationServiceImpl implements SmsNotificationService {

    @Override
    public void sendSms(String phoneNumber, String message) {
        // Simulate sending SMS
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }
}
