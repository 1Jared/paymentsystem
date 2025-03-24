package com.paymentsystem.task;

import com.paymentsystem.task.entity.PaymentRequest;
import com.paymentsystem.task.entity.PaymentResponse;
import com.paymentsystem.task.service.SmsNotificationService;
import com.paymentsystem.task.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class PaymentServiceTest {
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private SmsNotificationService smsNotificationService;

    public void B2CPaymentServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInitiatePayment() {
        PaymentRequest request = new PaymentRequest();
        request.setPhoneNumber("123456789");
        request.setAmount(100.0);

        PaymentResponse response = paymentService.initiatePayment(request);

        assertEquals("TX123456", response.getTransactionId());
        assertEquals("SUCCESS", response.getStatus());
        verify(smsNotificationService).sendSms("123456789", "Payment of 100.0 was successful.");
    }
}
