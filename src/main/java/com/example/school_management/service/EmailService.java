package com.example.school_management.service;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    private static final String FROM_EMAIL = "npranali961@gmail.com";
    private static final String APP_PASSWORD = "honw amuu rpnz anng";

    public void sendReceiptEmail(String toEmail, String studentName,
                                 String receiptNumber, byte[] pdfBytes) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Payment Receipt - " + receiptNumber);

            MimeMultipart multipart = new MimeMultipart();


            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(
                    "Dear " + studentName + ",\n\n" +
                            "Please find attached your payment receipt.\n\n" +
                            "Receipt Number : " + receiptNumber + "\n\n" +
                            "Thank you for your payment!\n\n" +
                            "Regards,\n" +
                            "ABC School"
            );

            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource dataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");
            attachmentPart.setDataHandler(new DataHandler(dataSource));
            attachmentPart.setFileName(receiptNumber + ".pdf");

            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email sent successfully to: " + toEmail);

        } catch (Exception e) {
            System.out.println("Email sending failed: " + e.getMessage());
        }
    }
}