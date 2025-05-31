package com.group3.ECommerce.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.secret.key}")
    private String secretKey;

    @Bean
    public void init() {
        Stripe.apiKey = secretKey;
    }
}