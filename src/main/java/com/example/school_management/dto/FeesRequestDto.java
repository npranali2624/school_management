package com.example.school_management.dto;

import com.example.school_management.constants.ValidationMessages;
import com.example.school_management.enums.FineType;
import com.example.school_management.enums.PaymentCycle;
import com.example.school_management.enums.Standard;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class FeesRequestDto {

    @NotNull(message = ValidationMessages.STANDARD_REQUIRED)
    private Standard std;

    @NotEmpty(message = ValidationMessages.FEE_ITEMS_REQUIRED)
    @Valid
    private List<FeeItemDTO> feeItems;

    @NotNull(message = ValidationMessages.PAYMENT_CYCLE_REQUIRED)
    private PaymentCycle paymentCycle;

    @NotBlank(message = ValidationMessages.ACADEMIC_YEAR_REQUIRED)
    @Pattern(
            regexp = "^(20\\d{2})-(20\\d{2})$",
            message = ValidationMessages.ACADEMIC_YEAR_PATTERN
    )
    private String academicYear;

    private FineType fineType;

    @DecimalMin(value = "0.0", message = ValidationMessages.FINE_AMOUNT_MIN)
    private BigDecimal fineAmount;

    @Data
    public static class FeeItemDTO {

        @NotNull(message = ValidationMessages.FEE_TYPE_REQUIRED)
        private com.example.school_management.enums.FeeType feeType;

        @NotNull(message = ValidationMessages.FEE_AMOUNT_REQUIRED)
        @DecimalMin(value = "0.01", message = ValidationMessages.FEE_AMOUNT_MIN)
        @Digits(integer = 10, fraction = 2, message = ValidationMessages.FEE_AMOUNT_FORMAT)
        private BigDecimal amount;
    }
}