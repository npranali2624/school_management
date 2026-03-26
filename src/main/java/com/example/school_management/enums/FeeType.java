package com.example.school_management.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeeType
{
    TUITION_FEES("Tuition Fees", "Main academic fee"),
    ADMISSION_FEES("Admission Fees", "Charged during new student entry"),
    EXAMINATION_FEES("Examination Fees", "For conducting exams"),
    TRANSPORT_FEES("Transport Fees", "If student uses bus service"),
    DEVELOPMENT_FEES("Development Fees", "Infrastructure & school maintenance"),
    ACTIVITY_FEES("Activity Fees", "Sports, events, extracurriculars"),
    MISCELLANEOUS_FEES("Miscellaneous Fees", "Covers small/unexpected expenses");

    private final String displayName;
    private final String description;
}