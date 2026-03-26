
package com.example.school_management.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class PaymentResponseDto{

    private String receiptNumber;
    private String status;
    private BigDecimal totalAmount;
    private String paymentMode;
    private String message;
}