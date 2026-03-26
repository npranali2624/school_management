package com.example.school_management.service;

import com.example.school_management.dto.PaymentRequestDto;
import com.example.school_management.dto.PaymentRequestDto;
import com.example.school_management.dto.PaymentResponseDto;
import com.example.school_management.dto.PaymentResponseDto;
import com.example.school_management.entity.Payment;
import com.example.school_management.enums.PaymentMode;
import com.example.school_management.enums.PaymentStatus;
import com.example.school_management.repo.PaymentRepository;
import com.example.school_management.service.PaymentService;
import com.example.school_management.Util.ReceiptGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;


    @Override
    public PaymentResponseDto makePayment(PaymentRequestDto request) {

        Payment payment = new Payment();

        // Required fields
        payment.setTotalAmount(request.getTotalAmount());
        payment.setPaymentDate(request.getPaymentDate());

        payment.setPaymentMode(PaymentMode.fromString(request.getPaymentMode()));
        payment.setRemarks(request.getRemarks());
        payment.setStatus(PaymentStatus.COMPLETED);

        // Optional fields
        payment.setTransactionId(request.getTransactionId());
        payment.setUpiId(request.getUpiId());
        payment.setBankName(request.getBankName());
        payment.setReferenceNumber(request.getReferenceNumber());
        payment.setChequeNumber(request.getChequeNumber());
        payment.setChequeDate(request.getChequeDate());

        // Optional: skip collectedBy if not testing Finance yet
//        payment.setCollectedBy(null);

        // Generate receipt
        String receipt = ReceiptGenerator.generateReceipt(payment.getPaymentMode());
        payment.setReceiptNumber(receipt);

        // Save
        paymentRepository.save(payment);

        // Response
        return PaymentResponseDto.builder()
                .receiptNumber(receipt)
                .status(payment.getStatus().name())
                .totalAmount(payment.getTotalAmount())
                .paymentMode(payment.getPaymentMode().name())
                .message("Payment Successful")
                .build();
    }
}