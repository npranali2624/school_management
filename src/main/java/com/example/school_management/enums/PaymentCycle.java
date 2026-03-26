package com.example.school_management.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentCycle
{
    ANNUAL("Annual Fees", "Paid once per year", 1),
    SEMESTER("Semester-wise Fees", "Paid twice a year (per semester)", 2);

    private final String displayName;
    private final String description;
    private final int installments;
}