package com.example.school_management.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FineType
{
    LATE_PAYMENT_FINE("Late Payment Fine"),
    EXAM_LATE_FEE("Exam Late Fee"),
    LIBRARY_FINE("Library Fine"),
    DAMAGE_FINE("Damage Fine");

    private final String displayName;
}
