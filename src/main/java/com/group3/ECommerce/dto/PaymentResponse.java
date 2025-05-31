package com.group3.ECommerce.dto;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}