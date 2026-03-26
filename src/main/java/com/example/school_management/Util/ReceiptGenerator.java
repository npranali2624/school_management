package com.example.school_management.Util;

import com.example.school_management.enums.PaymentMode;
import java.util.UUID;

public class ReceiptGenerator {


    public static String generateReceipt(PaymentMode mode) {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        return mode.name() + "-REC-" + uuid;
    }

}