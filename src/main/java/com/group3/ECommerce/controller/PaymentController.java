package com.group3.ECommerce.controller;


import com.group3.ECommerce.dto.PaymentRequest;
import com.group3.ECommerce.dto.PaymentResponse;
import com.group3.ECommerce.exception.PaymentException;
import com.group3.ECommerce.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            PaymentResponse paymentResponse = paymentService.createPaymentIntent(paymentRequest);
            return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
        } catch (PaymentException e) {
            return new ResponseEntity<>(new PaymentResponse(null, null, null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/capture/{paymentId}")
    public ResponseEntity<PaymentResponse> capturePayment(@PathVariable String paymentId) {
        try {
            PaymentResponse paymentResponse = paymentService.capturePayment(paymentId);
            return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
        } catch (PaymentException e) {
            return new ResponseEntity<>(new PaymentResponse(null, null, null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/refund/{paymentId}")
    public ResponseEntity<PaymentResponse> refundPayment(@PathVariable String paymentId) {
        try {
            PaymentResponse paymentResponse = paymentService.refundPayment(paymentId);
            return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
        } catch (PaymentException e) {
            return new ResponseEntity<>(new PaymentResponse(null, null, null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}