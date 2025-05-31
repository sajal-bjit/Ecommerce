package com.group3.ECommerce.service;


import com.group3.ECommerce.dto.PaymentRequest;
import com.group3.ECommerce.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPaymentIntent(PaymentRequest paymentRequest);
    PaymentResponse capturePayment(String paymentId);
    PaymentResponse refundPayment(String paymentId);
}