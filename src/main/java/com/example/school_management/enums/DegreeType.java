package com.example.school_management.enums;

public enum DegreeType {

    // Teacher Degrees
    B_ED("Bachelor of Education"),
    M_ED("Master of Education"),
    B_A("Bachelor of Arts"),
    M_A("Master of Arts"),
    B_SC("Bachelor of Science"),
    M_SC("Master of Science"),
    B_COM("Bachelor of Commerce"),
    M_COM("Master of Commerce"),
    B_TECH("Bachelor of Technology"),
    M_TECH("Master of Technology"),
    D_ED("Diploma in Education"),
    NTT("Nursery Teacher Training"),
    TTC("Teacher Training Certificate"),
    CTET("Central Teacher Eligibility Test Certified"),

    // Finance Staff Degrees
    B_COM_FINANCE("Bachelor of Commerce - Finance"),
    M_COM_FINANCE("Master of Commerce - Finance"),
    BBA("Bachelor of Business Administration"),
    MBA("Master of Business Administration"),
    CA("Chartered Accountant"),
    CMA("Cost and Management Accountant"),
    CS("Company Secretary"),
    B_SC_ACCOUNTING("Bachelor of Science in Accounting"),
    PGDM("Post Graduate Diploma in Management"),

    // Common / Other
    OTHER("Other - Please specify below");

    private final String displayName;

    DegreeType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}