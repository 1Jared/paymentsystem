package com.paymentsystem.task.controller;


import com.paymentsystem.task.entity.PaymentRequest;
import com.paymentsystem.task.entity.PaymentResponse;
import com.paymentsystem.task.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/b2c")
    @ApiOperation(value = "Initiating payment")
    public ResponseEntity<PaymentResponse> initiatePayment(@Validated @RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.initiatePayment(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    @ApiOperation(value = "Get payment status by transaction id")
    public ResponseEntity<PaymentResponse> getPaymentStatus(@PathVariable String transactionId) {
        PaymentResponse response = paymentService.getPaymentStatus(transactionId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "Get list of payment responses")
    public ResponseEntity<List<PaymentResponse>> listAllPayments() {
        List<PaymentResponse> payments = paymentService.listAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @DeleteMapping("/{transactionId}")
    @ApiOperation(value = "Cancel payment by transaction id")
    public ResponseEntity<String> cancelPayment(@PathVariable String transactionId) {
        paymentService.cancelPayment(transactionId);
        return new ResponseEntity<>("Payment cancelled successfully.", HttpStatus.OK);
    }

    @PostMapping("/resend-sms/{transactionId}")
    @ApiOperation(value = "Resend notification by transaction id")
    public ResponseEntity<String> resendSmsNotification(@PathVariable String transactionId) {
        paymentService.resendSmsNotification(transactionId);
        return new ResponseEntity<>("SMS notification resent successfully.", HttpStatus.OK);
    }
}
