package com.example.school_management.entity;

import com.example.school_management.enums.FineType;
import com.example.school_management.enums.PaymentCycle;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Fees
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "std", nullable = false, length = 20)
    private String std;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "fees_fee_items",
            joinColumns = @JoinColumn(name = "fees_id")
    )
    @Builder.Default
    private List<FeeItem> feeItems = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_cycle", nullable = false, length = 20)
    private PaymentCycle paymentCycle;
    @Column(name = "academic_year", nullable = false, length = 10)
    private String academicYear;


    @Setter(AccessLevel.NONE)
    @Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
    @Builder.Default
    private BigDecimal totalAmount = BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    @Column(name = "fine_type", length = 30)
    private FineType fineType;
    @Column(name = "fine_amount", precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal fineAmount = BigDecimal.ZERO;
    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @Column(name = "paid_at")
    private Instant paidAt;
    @UpdateTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "updated_at")
    private Instant updatedAt;

    public void recalculateTotalAmount()
    {
        this.totalAmount = feeItems.stream()
                .map(FeeItem::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public void markAsPaid()
    {
        recalculateTotalAmount();
        this.paidAt = Instant.now();
    }
    public boolean isPaid()
    {
        return this.paidAt != null;
    }
}
