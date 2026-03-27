package com.example.school_management.dto;

import com.example.school_management.enums.FineType;
import com.example.school_management.enums.PaymentCycle;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeesResponseDto {

    private Long id;
    private String std;        // ✅ changed Integer → String
    private List<FeeItemDTO> feeItems;
    private PaymentCycle paymentCycle;
    private String academicYear;
    private BigDecimal totalAmount;
    private FineType fineType;
    private BigDecimal fineAmount;
    private boolean paid;
    private Instant createdAt;
    private Instant paidAt;
    private Instant updatedAt;

    @Data
    @Builder
    public static class FeeItemDTO {
        private String feeType;
        private String feeTypeCode;
        private BigDecimal amount;
    }
}