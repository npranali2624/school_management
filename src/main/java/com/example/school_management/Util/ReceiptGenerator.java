package com.example.school_management.Util;

import com.example.school_management.enums.PaymentMode;

public class ReceiptGenerator {

    public static String generateReceipt(PaymentMode mode) {
        return mode.name() + "-REC-" + System.currentTimeMillis();
    }
}