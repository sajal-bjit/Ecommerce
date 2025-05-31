package com.group3.ECommerce.dto;

import lombok.Getter;

@Getter
public class PaymentResponse {
    private String id;
    private String status;
    private String transactionId;
    private String message;

    public PaymentResponse(String id, String status, String transactionId, String message) {
        this.id = id;
        this.status = status;
        this.transactionId = transactionId;
        this.message = message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}