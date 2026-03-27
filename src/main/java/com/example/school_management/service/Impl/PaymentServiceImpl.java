package com.example.school_management.service.Impl;

import com.example.school_management.Util.ReceiptPdfGenerator;
import com.example.school_management.dto.PaymentRequestDto;
import com.example.school_management.dto.PaymentResponseDto;
import com.example.school_management.entity.Payment;
import com.example.school_management.entity.Student;
import com.example.school_management.enums.PaymentStatus;
import com.example.school_management.enums.ModeOfPayment;
import com.example.school_management.repo.PaymentRepository;
import com.example.school_management.repo.StudentRepository;
import com.example.school_management.service.EmailService;
import com.example.school_management.service.PaymentService;
import com.example.school_management.Util.ReceiptGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final EmailService emailService;

    @Override
    public PaymentResponseDto makePayment(PaymentRequestDto request) {

        Payment payment = new Payment();

        // Required fields
        payment.setTotalAmount(request.getTotalAmount());
        payment.setPaymentDate(LocalDate.now());
        payment.setModeofpayment(ModeOfPayment.fromString(request.getModeofpayment()));
        payment.setRemarks(request.getRemarks());
        payment.setStatus(PaymentStatus.COMPLETED);

        // Optional fields
        payment.setTransactionId(request.getTransactionId());
        payment.setUpiId(request.getUpiId());
        payment.setBankName(request.getBankName());
        payment.setChequeNumber(request.getChequeNumber());
        payment.setChequeDate(request.getChequeDate());

        // Fetch student and set
        Student student = null;
        if (request.getStudentId() != null) {
            student = studentRepository.findById(request.getStudentId())
                    .orElseThrow(() -> new RuntimeException(
                            "Student not found with id: " + request.getStudentId()));
            payment.setStudent(student);
        }

        // Auto-generate receipt and reference number
        String receipt = ReceiptGenerator.generateReceipt(payment.getModeofpayment());
        String referenceNumber = ReceiptGenerator.generateReferenceNumber(payment.getModeofpayment());
        payment.setReceiptNumber(receipt);
        payment.setReferenceNumber(referenceNumber);

        // Save
        paymentRepository.save(payment);

        // ✅ Send email — access email via student.getParent()
        if (student != null
                && student.getParent() != null
                && student.getParent().getEmail() != null) {   // ✅ fixed
            try {
                byte[] pdfBytes = ReceiptPdfGenerator.generateReceiptPdf(payment);
                String studentName = student.getFirstName() + " " + student.getLastName();

                emailService.sendReceiptEmail(
                        student.getParent().getEmail(),        // ✅ fixed
                        studentName,
                        receipt,
                        pdfBytes
                );
            } catch (Exception e) {
                // Payment won't fail if email fails
                System.out.println("Email sending failed: " + e.getMessage());
            }
        }

        // Response
        return PaymentResponseDto.builder()
                .receiptNumber(receipt)
                .referenceNumber(referenceNumber)
                .paymentDate(payment.getPaymentDate())
                .status(payment.getStatus().name())
                .totalAmount(payment.getTotalAmount())
                .Modeofpayment(payment.getModeofpayment().name())
                .message("Payment Successful")
                .build();
    }

    @Override
    public byte[] downloadReceiptPdf(String receiptNumber) {

        Payment payment = paymentRepository.findByReceiptNumber(receiptNumber)
                .orElseThrow(() -> new RuntimeException(
                        "Payment not found for receipt: " + receiptNumber));

        try {
            return ReceiptPdfGenerator.generateReceiptPdf(payment);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate receipt PDF", e);
        }
    }
}