package com.group3.ECommerce.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.api.key}")
    private String apiKey;

    @Bean
    public StripeInitializer stripeInitializer() {
        Stripe.apiKey = apiKey;
        return new StripeInitializer();
    }

    public static class StripeInitializer {
        // Empty class, just to satisfy @Bean return type
    }
}