package com.example.school_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentRequestDto {

    private BigDecimal totalAmount;
    private LocalDate paymentDate;

    @JsonProperty("Modeofpayment")
    private String Modeofpayment;

    private String transactionId;
    private String upiId;

    private String chequeNumber;
    private LocalDate chequeDate;

    private String bankName;
    private String referenceNumber;

    private Long collectedById;
    private String remarks;

    private Long studentId;
}