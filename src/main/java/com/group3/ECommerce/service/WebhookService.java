package com.group3.ECommerce.service;

import com.group3.ECommerce.exception.PaymentException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class WebhookService {

    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);

    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

    @PostConstruct
    public void init() {
        logger.info("WebhookService initialized with secret: {}", webhookSecret);
    }

    public void handleWebhook(String payload, String sigHeader) {
        try {
            Event event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
            processEvent(event);
        } catch (Exception e) {
            logger.error("Error handling webhook: {}", e.getMessage());
            throw new PaymentException("Webhook handling failed", e);
        }
    }

    private void processEvent(Event event) {
        // Implement event processing logic based on event type
        switch (event.getType()) {
            case "payment_intent.succeeded":
                // Handle successful payment intent
                break;
            case "payment_intent.payment_failed":
                // Handle failed payment intent
                break;
            // Add more event types as needed
            default:
                logger.warn("Unhandled event type: {}", event.getType());
        }
    }
}