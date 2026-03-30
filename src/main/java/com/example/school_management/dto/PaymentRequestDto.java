package com.example.school_management.dto;

import com.example.school_management.constants.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentRequestDto {

    @NotNull(message = ValidationMessages.PAYMENT_AMOUNT_REQUIRED)
    @Positive(message = ValidationMessages.PAYMENT_AMOUNT_POSITIVE)
    private BigDecimal totalAmount;

    @NotNull(message = ValidationMessages.PAYMENT_DATE_REQUIRED)
    private LocalDate paymentDate;

    @NotNull(message = ValidationMessages.PAYMENT_MODE_REQUIRED)
    @JsonProperty("Modeofpayment")
    private String Modeofpayment;

    @Size(max = 100, message = ValidationMessages.TRANSACTION_ID_MAX)
    private String transactionId;

    @Size(max = 50, message = ValidationMessages.UPI_ID_MAX)
    private String upiId;

    @Size(max = 50, message = ValidationMessages.CHEQUE_NUMBER_MAX)
    private String chequeNumber;

    private LocalDate chequeDate;

    @Size(max = 100, message = ValidationMessages.BANK_NAME_MAX)
    private String bankName;

    @Size(max = 100, message = ValidationMessages.REFERENCE_NUMBER_MAX)
    private String referenceNumber;

    private Long collectedById;

    @Size(max = 500, message = ValidationMessages.REMARKS_MAX)
    private String remarks;

    private Long studentId;
}