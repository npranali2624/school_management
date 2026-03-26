package com.example.school_management.dto;

import com.example.school_management.enums.FineType;
import com.example.school_management.enums.PaymentCycle;
import com.example.school_management.enums.Standard;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class FeesRequestDto
{
    @NotNull(message = "Standard is required")
    private Standard std;

    @NotEmpty(message = "At least one fee item is required")
    @Valid
    private List<FeeItemDTO> feeItems;

    @NotNull(message = "Payment cycle is required")
    private PaymentCycle paymentCycle;

    @NotBlank(message = "Academic year is required")
    @Pattern(
            regexp = "^(20\\d{2})-(20\\d{2})$",
            message = "Academic year must be in format YYYY-YYYY (e.g., 2024-2025)"
    )
    private String academicYear;

    private FineType fineType;

    @DecimalMin(value = "0.0", message = "Fine amount cannot be negative")
    private BigDecimal fineAmount;

    @Data
    public static class FeeItemDTO {

        @NotNull(message = "Fee type is required")
        private com.example.school_management.enums.FeeType feeType;

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
        @Digits(integer = 10, fraction = 2, message = "Invalid amount format")
        private BigDecimal amount;
    }
}