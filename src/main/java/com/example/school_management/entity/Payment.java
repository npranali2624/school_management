package com.example.school_management.entity;

import com.example.school_management.constants.ValidationMessages;
import com.example.school_management.enums.PaymentStatus;
import com.example.school_management.enums.ModeOfPayment;
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

    @NotNull(message = ValidationMessages.PAYMENT_AMOUNT_REQUIRED)
    @Positive(message = ValidationMessages.PAYMENT_AMOUNT_POSITIVE)
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @NotNull(message = ValidationMessages.PAYMENT_DATE_REQUIRED)
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @NotNull(message = ValidationMessages.PAYMENT_MODE_REQUIRED)
    @Enumerated(EnumType.STRING)
    @Column(name = "mode_of_payment", nullable = false)
    private ModeOfPayment Modeofpayment;

    // UPI
    @Size(max = 100, message = ValidationMessages.TRANSACTION_ID_MAX)
    @Column(name = "transaction_id", length = 100)
    private String transactionId;

    @Size(max = 50, message = ValidationMessages.UPI_ID_MAX)
    @Column(name = "upi_id", length = 50)
    private String upiId;

    // CHECK / DEMAND_DRAFT
    @Size(max = 50, message = ValidationMessages.CHEQUE_NUMBER_MAX)
    @Column(name = "cheque_number", length = 50)
    private String chequeNumber;

    @Column(name = "cheque_date")
    private LocalDate chequeDate;

    // NET_BANKING / CARD
    @Size(max = 100, message = ValidationMessages.BANK_NAME_MAX)
    @Column(name = "bank_name", length = 100)
    private String bankName;

    @Size(max = 100, message = ValidationMessages.REFERENCE_NUMBER_MAX)
    @Column(name = "reference_number", length = 100)
    private String referenceNumber;

    @Size(max = 50, message = ValidationMessages.RECEIPT_NUMBER_MAX)
    @Column(name = "receipt_number", length = 50, unique = true, updatable = false)
    private String receiptNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collected_by_id", nullable = true)
    private FinanceOfficer collectedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fees_id", nullable = true)
    private Fees fees;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = true)
    private Student student;

    @Size(max = 500, message = ValidationMessages.REMARKS_MAX)
    @Column(name = "remarks", length = 500)
    private String remarks;
}