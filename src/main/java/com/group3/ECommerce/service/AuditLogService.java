package com.group3.ECommerce.service;

import com.group3.ECommerce.productListing.model.Product;

public interface AuditLogService {
    void logProductCreation(Product product, String username);
}
