package com.paymentsystem.task.service.impl;

import com.paymentsystem.task.entity.PaymentRequest;
import com.paymentsystem.task.entity.PaymentResponse;
import com.paymentsystem.task.service.MockMnoPaymentService;
import com.paymentsystem.task.service.PaymentService;
import com.paymentsystem.task.service.SmsNotificationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final MockMnoPaymentService mnoPaymentService;
    private final SmsNotificationService smsNotificationService;
    private final Map<String, PaymentResponse> paymentDatabase = new HashMap<>();

    public PaymentServiceImpl(MockMnoPaymentService mnoPaymentService, SmsNotificationService smsNotificationService) {
        this.mnoPaymentService = mnoPaymentService;
        this.smsNotificationService = smsNotificationService;
    }

    @Override
    public PaymentResponse initiatePayment(PaymentRequest request) {
        // Use the mock MNO payment service to process the payment
        boolean paymentSuccess = mnoPaymentService.processPayment(request.getPhoneNumber(), request.getAmount());

        // Simulate payment processing
        PaymentResponse response = new PaymentResponse();
        response.setTransactionId("TX" + System.currentTimeMillis());
        response.setStatus("SUCCESS");

        // Store payment in the database
        paymentDatabase.put(response.getTransactionId(), response);

        // Send SMS notification
        smsNotificationService.sendSms(request.getPhoneNumber(), "Payment of " + request.getAmount() + " was successful.");

        return response;
    }

    @Override
    public PaymentResponse getPaymentStatus(String transactionId) {
        return paymentDatabase.get(transactionId);
    }

    @Override
    public List<PaymentResponse> listAllPayments() {
        return new ArrayList<>(paymentDatabase.values());
    }

    @Override
    public void cancelPayment(String transactionId) {
        PaymentResponse payment = paymentDatabase.get(transactionId);
        if (payment != null) {
            payment.setStatus("CANCELLED");
        }
    }

    @Override
    public void resendSmsNotification(String transactionId) {
        PaymentResponse payment = paymentDatabase.get(transactionId);
        if (payment != null) {
            // Simulate sending SMS again
            smsNotificationService.sendSms("recipient-phone-number", "Payment of " + payment.getTransactionId() + " was successful.");
        }
    }
}