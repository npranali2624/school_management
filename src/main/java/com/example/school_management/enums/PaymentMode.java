package com.example.school_management.enums;

public enum PaymentMode
{
    CASH,
    UPI,
    CHEQUE,
    NET_BANKING,
    DEMAND_DRAFT,
    CARD;

    public static PaymentMode fromString(String mode) {
        if (mode == null) throw new IllegalArgumentException("Payment mode cannot be null");

        switch (mode.toUpperCase()) {
            case "CHECK": return CHEQUE;  // map incorrect input to correct enum
            default: return PaymentMode.valueOf(mode.toUpperCase());
        }
    }
}