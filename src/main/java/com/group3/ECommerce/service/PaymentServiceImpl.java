package com.group3.ECommerce.service;


import com.group3.ECommerce.dto.PaymentRequest;
import com.group3.ECommerce.dto.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Override
    public PaymentResponse createPaymentIntent(PaymentRequest paymentRequest) {
        return null;
    }

    @Override
    public PaymentResponse capturePayment(String paymentId) {
        return null;
    }

    @Override
    public PaymentResponse refundPayment(String paymentId) {
        return null;
    }
}