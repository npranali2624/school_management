package com.example.school_management.service.Impl;

import com.example.school_management.Util.ReceiptPdfGenerator;
import com.example.school_management.Util.ReceiptGenerator;
import com.example.school_management.dto.PaymentRequestDto;
import com.example.school_management.dto.PaymentResponseDto;
import com.example.school_management.entity.Payment;
import com.example.school_management.entity.Student;
import com.example.school_management.Mapper.PaymentMapper;
import com.example.school_management.repo.PaymentRepository;
import com.example.school_management.repo.StudentRepository;
import com.example.school_management.service.EmailService;
import com.example.school_management.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final EmailService emailService;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponseDto makePayment(PaymentRequestDto request) {

        Student student = null;
        if (request.getStudentId() != null) {
            student = studentRepository.findById(request.getStudentId())
                    .orElseThrow(() -> new RuntimeException(
                            "Student not found with id: " + request.getStudentId()));
        }

        Payment payment = paymentMapper.toEntity(request, student);

        String receipt = ReceiptGenerator.generateReceipt(payment.getModeofpayment());
        String referenceNumber = ReceiptGenerator.generateReferenceNumber(payment.getModeofpayment());
        payment.setReceiptNumber(receipt);
        payment.setReferenceNumber(referenceNumber);

        paymentRepository.save(payment);

        sendReceiptEmailIfApplicable(student, payment, receipt);

        return paymentMapper.toResponseDto(payment, "Payment Successful");
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

    private void sendReceiptEmailIfApplicable(Student student, Payment payment, String receipt) {
        if (student == null
                || student.getParent() == null
                || student.getParent().getEmail() == null) {
            return;
        }

        try {
            byte[] pdfBytes = ReceiptPdfGenerator.generateReceiptPdf(payment);
            String studentName = student.getFirstName() + " " + student.getLastName();
            emailService.sendReceiptEmail(
                    student.getParent().getEmail(),
                    studentName,
                    receipt,
                    pdfBytes
            );
        } catch (Exception e) {
            System.out.println("Email sending failed: " + e.getMessage());
        }
    }
}