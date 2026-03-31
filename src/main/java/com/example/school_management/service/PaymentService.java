package com.example.school_management.service;

import com.example.school_management.dto.PaymentRequestDto;
import com.example.school_management.dto.PaymentResponseDto;

public interface PaymentService {

    PaymentResponseDto makePayment(PaymentRequestDto request);

    byte[] downloadReceiptPdf(String receiptNumber);
}