package com.paymentsystem.task.service;


import com.paymentsystem.task.entity.PaymentRequest;
import com.paymentsystem.task.entity.PaymentResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse initiatePayment(PaymentRequest request);
    PaymentResponse getPaymentStatus(String transactionId);
    List<PaymentResponse> listAllPayments();
    void cancelPayment(String transactionId);
    void resendSmsNotification(String transactionId);
}
