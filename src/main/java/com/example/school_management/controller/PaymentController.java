package com.example.school_management.controller;

import com.example.school_management.dto.ApiResponse;
import com.example.school_management.dto.PaymentRequestDto;
import com.example.school_management.dto.PaymentResponseDto;
import com.example.school_management.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentResponseDto>> makePayment(
            @RequestBody PaymentRequestDto request) {

        PaymentResponseDto response = paymentService.makePayment(request);

        return ResponseEntity.ok(
                ApiResponse.ok("Payment successful", response)
        );
    }

    @GetMapping("/receipt/{receiptNumber}/download")
    public ResponseEntity<byte[]> downloadReceipt(
            @PathVariable String receiptNumber) {

        byte[] pdf = paymentService.downloadReceiptPdf(receiptNumber);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=receipt-" + receiptNumber + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}