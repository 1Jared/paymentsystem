package com.paymentsystem.task.service;

public interface SmsNotificationService {
    void sendSms(String phoneNumber, String message);
}
