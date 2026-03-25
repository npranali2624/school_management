package com.example.school_management.entity;

import com.example.school_management.enums.PaymentMode;
import com.example.school_management.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class Payment extends BaseEntity {



    @NotNull
    @Positive
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @NotNull
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;



    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode", nullable = false)
    private PaymentMode paymentMode;

    // UPI
    @Size(max = 100)
    @Column(name = "transaction_id", length = 100)
    private String transactionId;

    @Size(max = 50)
    @Column(name = "upi_id", length = 50)
    private String upiId;

    // CHECK / DEMAND_DRAFT
    @Size(max = 50)
    @Column(name = "check_number", length = 50)
    private String checkNumber;

    @Column(name = "check_date")
    private LocalDate checkDate;

    // NET_BANKING / CARD
    @Size(max = 100)
    @Column(name = "bank_name", length = 100)
    private String bankName;

    @Size(max = 100)
    @Column(name = "reference_number", length = 100)
    private String referenceNumber;


    @Size(max = 50)
    @Column(name = "receipt_number", length = 50, unique = true)
    private String receiptNumber;



    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collected_by_id", nullable = false)
    private Finance collectedBy;



    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;
}