package com.example.school_management.enums;

public enum ModeOfPayment
{
    CASH,
    UPI,
    CHEQUE,
    NET_BANKING,
    DEMAND_DRAFT,
    CARD;

    public static ModeOfPayment fromString(String mode) {
        if (mode == null) throw new IllegalArgumentException("Payment mode cannot be null");

        switch (mode.toUpperCase()) {
            case "CHECK": return CHEQUE;  // map incorrect input to correct enum
            default: return ModeOfPayment.valueOf(mode.toUpperCase());
        }
    }
}