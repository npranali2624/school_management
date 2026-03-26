package com.example.school_management.Mapper;

import com.example.school_management.dto.FeesRequestDto;
import com.example.school_management.dto.FeesResponseDto;
import com.example.school_management.entity.FeeItem;
import com.example.school_management.entity.Fees;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
public class FeesMapper {
    public Fees toEntity(FeesRequestDto dto) {
        List<FeeItem> feeItems = dto.getFeeItems().stream()
                .map(item -> FeeItem.builder()
                        .feeType(item.getFeeType())
                        .amount(item.getAmount())
                        .build())
                .toList();

        Fees fees = Fees.builder()
                .std(dto.getStd())
                .feeItems(feeItems)
                .paymentCycle(dto.getPaymentCycle())
                .academicYear(dto.getAcademicYear())
                .fineType(dto.getFineType())
                .fineAmount(dto.getFineAmount() != null ? dto.getFineAmount() : BigDecimal.ZERO)
                .build();

        fees.recalculateTotalAmount();
        return fees;
    }

    public FeesResponseDto toResponseDTO(Fees fees) {
        List<FeesResponseDto.FeeItemDTO> feeItemDTOs = fees.getFeeItems().stream()
                .map(item -> FeesResponseDto.FeeItemDTO.builder()
                        .feeType(item.getFeeType().getDisplayName())
                        .feeTypeCode(item.getFeeType().name())
                        .amount(item.getAmount())
                        .build())
                .toList();

        return FeesResponseDto.builder()
                .id(fees.getId())
                .std(fees.getStd())
                .feeItems(feeItemDTOs)
                .paymentCycle(fees.getPaymentCycle())
                .academicYear(fees.getAcademicYear())
                .totalAmount(fees.getTotalAmount())
                .fineType(fees.getFineType())
                .fineAmount(fees.getFineAmount())
                .paid(fees.isPaid())
                .createdAt(fees.getCreatedAt())
                .paidAt(fees.getPaidAt())
                .updatedAt(fees.getUpdatedAt())
                .build();
    }
}