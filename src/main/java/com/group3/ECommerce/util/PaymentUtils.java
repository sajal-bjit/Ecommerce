package com.group3.ECommerce.util;

import com.group3.ECommerce.dto.PaymentRequest;
import org.springframework.util.StringUtils;

public class PaymentUtils {

    public static boolean validatePaymentRequest(PaymentRequest request) {
        return request != null &&
             //  StringUtils.hasText(request.getAmount()) &&
               StringUtils.hasText(request.getCurrency()) &&
               StringUtils.hasText(request.getPaymentMethodId());
    }
}