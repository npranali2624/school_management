package com.example.school_management.repo;

import com.example.school_management.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByReceiptNumber(String receiptNumber);
}