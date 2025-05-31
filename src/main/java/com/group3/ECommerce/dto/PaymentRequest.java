package com.group3.ECommerce.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public class PaymentRequest {

    @NotBlank(message = "Currency is required")
    private String currency;

    @NotNull(message = "Amount is required")
    private Long amount;

    @NotBlank(message = "Payment method ID is required")
    private String paymentMethodId;

    @NotBlank(message = "Description is required")
    private String description;

    // Getters and Setters

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}