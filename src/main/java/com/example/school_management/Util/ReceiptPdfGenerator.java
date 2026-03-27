package com.example.school_management.Util;

import com.example.school_management.entity.FeeItem;
import com.example.school_management.entity.Fees;
import com.example.school_management.entity.Payment;
import com.example.school_management.entity.Student;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptPdfGenerator {

    // ✅ Hardcoded school details — change as per your school
    private static final String SCHOOL_NAME    = "ABC SCHOOL";
    private static final String SCHOOL_ADDRESS = "123 Main Street, Mumbai, Maharashtra - 400001";
    private static final String SCHOOL_PHONE   = "9876543210";
    private static final String SCHOOL_EMAIL   = "school@gmail.com";

    private static final String LINE = "------------------------------------------------------------";

    public static byte[] generateReceiptPdf(Payment payment) throws DocumentException {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        // ── Fonts ───────────────────────────────────────────
        Font schoolFont  = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Font addressFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
        Font titleFont   = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
        Font labelFont   = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font valueFont   = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
        Font footerFont  = new Font(Font.FontFamily.HELVETICA, 9, Font.ITALIC);

        // ── School Header ────────────────────────────────────
        Paragraph schoolName = new Paragraph(SCHOOL_NAME, schoolFont);
        schoolName.setAlignment(Element.ALIGN_CENTER);
        document.add(schoolName);

        Paragraph schoolInfo = new Paragraph(
                SCHOOL_ADDRESS + "\n" +
                        "Phone: " + SCHOOL_PHONE + " | Email: " + SCHOOL_EMAIL, addressFont);
        schoolInfo.setAlignment(Element.ALIGN_CENTER);
        document.add(schoolInfo);

        document.add(Chunk.NEWLINE);

        // ── Receipt Title ────────────────────────────────────
        Paragraph title = new Paragraph("PAYMENT RECEIPT", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph(LINE, valueFont));
        document.add(Chunk.NEWLINE);

        // ── Receipt Info ─────────────────────────────────────
        document.add(new Paragraph("Receipt No      : " + payment.getReceiptNumber(), labelFont));
        document.add(new Paragraph("Reference No    : " + payment.getReferenceNumber(), valueFont));
        document.add(new Paragraph("Payment Date    : " + payment.getPaymentDate(), valueFont));

        document.add(Chunk.NEWLINE);
        document.add(new Paragraph(LINE, valueFont));
        document.add(Chunk.NEWLINE);

        // ── Student Details ──────────────────────────────────
        if (payment.getStudent() != null) {
            Student s = payment.getStudent();

            String fullName = s.getFirstName() + " " +
                    (s.getMiddleName() != null ? s.getMiddleName() + " " : "") +
                    s.getLastName();

            document.add(new Paragraph("STUDENT DETAILS", sectionFont));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Student Name    : " + fullName, valueFont));
            document.add(new Paragraph("Roll Number     : " + s.getRollNumber(), valueFont));


            // BEFORE (wrong)
            if (s.getParent() != null) {
                document.add(new Paragraph(
                        "Contact         : " + s.getParent().getMobilePrimary(), valueFont));

                document.add(new Paragraph(
                        "Address         : " + s.getParent().getAddressLine1(), valueFont));
            }


            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(LINE, valueFont));
            document.add(Chunk.NEWLINE);
        }

        // ── Fee Details ──────────────────────────────────────
        if (payment.getFees() != null) {
            Fees fees = payment.getFees();

            document.add(new Paragraph("FEE DETAILS", sectionFont));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Academic Year   : " + fees.getAcademicYear(), valueFont));
            document.add(new Paragraph("Standard        : " + fees.getStd(), valueFont));
            document.add(new Paragraph("Payment Cycle   : " + fees.getPaymentCycle(), valueFont));

            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Fee Breakdown   :", labelFont));
            for (FeeItem item : fees.getFeeItems()) {
                document.add(new Paragraph(
                        "  " + item.getFeeType() + "  :  Rs. " + item.getAmount(), valueFont));
            }

            document.add(new Paragraph("Fine Amount     : Rs. " + fees.getFineAmount(), valueFont));
            document.add(new Paragraph("------------------------", valueFont));
            document.add(new Paragraph("Total Amount    : Rs. " + payment.getTotalAmount(), labelFont));

            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(LINE, valueFont));
            document.add(Chunk.NEWLINE);
        }

        // ── Payment Details ──────────────────────────────────
        document.add(new Paragraph("PAYMENT DETAILS", sectionFont));
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("Payment Mode    : " + payment.getModeofpayment().name(), valueFont));
        document.add(new Paragraph("Status          : " + payment.getStatus().name(), valueFont));

        // ✅ Dynamic based on payment mode
        switch (payment.getModeofpayment()) {
            case UPI:
                document.add(new Paragraph("UPI ID          : " + payment.getUpiId(), valueFont));
                document.add(new Paragraph("Transaction ID  : " + payment.getTransactionId(), valueFont));
                break;
            case CHEQUE:
            case DEMAND_DRAFT:
                document.add(new Paragraph("Cheque Number   : " + payment.getChequeNumber(), valueFont));
                document.add(new Paragraph("Cheque Date     : " + payment.getChequeDate(), valueFont));
                break;
            case NET_BANKING:
            case CARD:
                document.add(new Paragraph("Bank Name       : " + payment.getBankName(), valueFont));
                document.add(new Paragraph("Transaction ID  : " + payment.getTransactionId(), valueFont));
                break;
            case CASH:
                document.add(new Paragraph("No additional details", valueFont));
                break;
        }

        if (payment.getRemarks() != null) {
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Remarks         : " + payment.getRemarks(), valueFont));
        }

        document.add(Chunk.NEWLINE);
        document.add(new Paragraph(LINE, valueFont));
        document.add(Chunk.NEWLINE);

        // ── Footer ───────────────────────────────────────────
        String generatedOn = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        Paragraph footer1 = new Paragraph("Generated On : " + generatedOn, footerFont);
        footer1.setAlignment(Element.ALIGN_CENTER);
        document.add(footer1);

        Paragraph footer2 = new Paragraph(
                "This is a computer generated receipt. No signature required.", footerFont);
        footer2.setAlignment(Element.ALIGN_CENTER);
        document.add(footer2);

        document.add(Chunk.NEWLINE);

        Paragraph thankYou = new Paragraph("Thank you for your payment!", titleFont);
        thankYou.setAlignment(Element.ALIGN_CENTER);
        document.add(thankYou);

        document.close();
        return out.toByteArray();
    }
}