package com.group3.ECommerce.service.impl;

import com.group3.ECommerce.productListing.model.Product;
import com.group3.ECommerce.productListing.service.AuditLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    private static final Logger logger = LoggerFactory.getLogger(AuditLogServiceImpl.class);

    @Override
    public void logProductCreation(Product product, String username) {
        // Simplified logging without accessing possibly unavailable getter methods
        logger.info("Product created by user: {}", username);
    }
}