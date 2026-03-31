package com.example.school_management.Util;

import com.example.school_management.enums.ModeOfPayment;

import java.util.UUID;

public class ReceiptGenerator {

    public static String generateReceipt(ModeOfPayment mode) {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        return mode.name() + "-REC-" + uuid;
    }

    public static String generateReferenceNumber(ModeOfPayment mode) {
        return mode.name() + "-REF-" + System.currentTimeMillis();
    }
}