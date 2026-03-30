package com.example.school_management.constants;

public class ValidationMessages {

    // ─── Names ───────────────────────────────────────
    public static final String FIRST_NAME_REQUIRED = "First name is required";
    public static final String FIRST_NAME_MAX = "First name must not exceed 50 characters";

    public static final String MIDDLE_NAME_MAX = "Middle name must not exceed 50 characters";

    public static final String LAST_NAME_REQUIRED = "Last name is required";
    public static final String LAST_NAME_MAX = "Last name must not exceed 50 characters";

    // ─── Personal Info ────────────────────────────────
    public static final String GENDER_REQUIRED = "Gender is required";
    public static final String DOB_REQUIRED = "Date of birth is required";
    public static final String DOB_PAST = "Date of birth must be in the past";
    public static final String BLOOD_GROUP_REQUIRED = "Blood group is required";
    public static final String NATIONALITY_REQUIRED = "Nationality is required";
    public static final String NATIONALITY_MAX = "Nationality must not exceed 50 characters";
    public static final String RELIGION_REQUIRED = "Religion is required";
    public static final String CATEGORY_REQUIRED = "Category is required";

    // ─── Roll Number ──────────────────────────────────
    public static final String ROLL_NUMBER_REQUIRED = "Roll number is required";
    public static final String ROLL_NUMBER_MIN = "Roll number must be at least 1";

    // ─── Aadhar ───────────────────────────────────────
    public static final String AADHAR_REQUIRED = "Aadhar number is required";
    public static final String AADHAR_PATTERN = "Aadhar number must be exactly 12 digits";

    // ─── Contact ──────────────────────────────────────
    public static final String MOBILE_PRIMARY_REQUIRED = "Primary mobile is required";
    public static final String MOBILE_PRIMARY_PATTERN = "Primary mobile must be 10 digits";
    public static final String MOBILE_ALTERNATE_PATTERN = "Alternate mobile must be 10 digits";
    public static final String EMAIL_INVALID = "Email is invalid";

    // ─── Address ──────────────────────────────────────
    public static final String ADDRESS_LINE1_REQUIRED = "Address line 1 is required";
    public static final String CITY_REQUIRED = "City is required";
    public static final String STATE_REQUIRED = "State is required";
    public static final String PINCODE_REQUIRED = "Pincode is required";
    public static final String PINCODE_PATTERN = "Pincode must be 6 digits";

    // ─── Academic ─────────────────────────────────────
    public static final String PREVIOUS_SCHOOL_MAX = "Previous school name must not exceed 150 characters";
    public static final String PERCENTAGE_MIN = "Percentage cannot be negative";
    public static final String PERCENTAGE_MAX = "Percentage cannot exceed 100";

    // ─── Documents ────────────────────────────────────
    public static final String BIRTH_CERTIFICATE_REQUIRED = "Birth certificate is required";
    public static final String AADHAR_PHOTO_REQUIRED = "Aadhar photo is required";
    public static final String PASSPORT_PHOTO_REQUIRED = "Passport-size photo is required";


    // ─── Parent ───────────────────────────────────────
    // ─── Parent (New ones only) ───────────────────────
    public static final String FATHER_FIRST_NAME_REQUIRED = "Father's first name is required";
    public static final String FATHER_LAST_NAME_REQUIRED = "Father's last name is required";
    public static final String MOTHER_FIRST_NAME_REQUIRED = "Mother's first name is required";
    public static final String MOTHER_LAST_NAME_REQUIRED = "Mother's last name is required";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String PASSWORD_MIN = "Password must be at least 6 characters";

    // ─── Payment ──────────────────────────────────────
    public static final String PAYMENT_AMOUNT_REQUIRED = "Total amount is required";
    public static final String PAYMENT_AMOUNT_POSITIVE = "Total amount must be positive";
    public static final String PAYMENT_DATE_REQUIRED = "Payment date is required";
    public static final String PAYMENT_MODE_REQUIRED = "Mode of payment is required";

    // ─── Payment Field Sizes ──────────────────────────
    public static final String TRANSACTION_ID_MAX = "Transaction ID must not exceed 100 characters";
    public static final String UPI_ID_MAX = "UPI ID must not exceed 50 characters";
    public static final String CHEQUE_NUMBER_MAX = "Cheque number must not exceed 50 characters";
    public static final String BANK_NAME_MAX = "Bank name must not exceed 100 characters";
    public static final String REFERENCE_NUMBER_MAX = "Reference number must not exceed 100 characters";
    public static final String RECEIPT_NUMBER_MAX = "Receipt number must not exceed 50 characters";
    public static final String REMARKS_MAX = "Remarks must not exceed 500 characters";

    // ─── Class ────────────────────────────────────────
    public static final String STANDARD_REQUIRED = "Standard is required";
    public static final String DIVISION_REQUIRED = "Division is required";


    // ─── Fees ─────────────────────────────────────────

    public static final String FEE_ITEMS_REQUIRED = "At least one fee item is required";
    public static final String PAYMENT_CYCLE_REQUIRED = "Payment cycle is required";
    public static final String ACADEMIC_YEAR_REQUIRED = "Academic year is required";
    public static final String ACADEMIC_YEAR_PATTERN = "Academic year must be in format YYYY-YYYY (e.g., 2024-2025)";
    public static final String FINE_AMOUNT_MIN = "Fine amount cannot be negative";

    // ─── FeeItem ──────────────────────────────────────
    public static final String FEE_TYPE_REQUIRED = "Fee type is required";
    public static final String FEE_AMOUNT_REQUIRED = "Amount is required";
    public static final String FEE_AMOUNT_MIN = "Amount must be greater than 0";
    public static final String FEE_AMOUNT_FORMAT = "Invalid amount format";


    // ─── Teacher ──────────────────────────────────────
    public static final String MIDDLE_NAME_REQUIRED = "Middle name is required";
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String EMAIL_FORMAT_INVALID = "Invalid email format";
    public static final String PASSWORD_PATTERN = "Password must have uppercase, lowercase, digit, and special character";
    public static final String PASSWORD_SIZE = "Password must be between 8 and 20 characters";
    public static final String MOBILE_REQUIRED = "Mobile is required";
    public static final String MOBILE_PATTERN = "Mobile must be 10 digits";
    public static final String STREET_REQUIRED = "Street is required";
    public static final String DEGREE_TYPE_REQUIRED = "Degree type is required";
    public static final String CUSTOM_DEGREE_MAX = "Custom degree name must not exceed 150 characters";
    public static final String JOINING_DATE_REQUIRED = "Joining date is required";
    public static final String PAN_REQUIRED = "PAN number is required";
    public static final String PAN_PATTERN = "Invalid PAN format";
    public static final String AADHAAR_PHOTO_REQUIRED = "Aadhaar photo URL is required";  // already added — skip!
    public static final String PAN_PHOTO_REQUIRED = "PAN photo URL is required";
    public static final String DEGREE_CERTIFICATE_REQUIRED = "Degree certificate URL is required";
    public static final String RESIGNATION_LETTER_REQUIRED = "Resignation letter URL is required";
    public static final String RESUME_REQUIRED = "Resume URL is required";
    public static final String EXPERIENCE_REQUIRED = "Years of experience is required";
    public static final String EXPERIENCE_POSITIVE = "Years of experience must be zero or positive";
    public static final String SUBJECT_REQUIRED = "Subject is required";
    public static final String ASSIGNED_CLASS_REQUIRED = "Assigned class is required";
    public static final String PREVIOUS_SCHOOL_REQUIRED = "Previous school is required";

    // ─── Subject ──────────────────────────────────────
    public static final String SUBJECT_NAME_REQUIRED = "Subject name is required";
    public static final String WEEKLY_HOURS_REQUIRED = "Weekly hours is required";
    public static final String WEEKLY_HOURS_MIN = "Weekly hours must be at least 1";
    public static final String SUBJECT_TYPE_REQUIRED = "Subject type is required";
    public static final String THEORY_MARKS_MIN = "Theory marks cannot be negative";
    public static final String INTERNAL_MARKS_MIN = "Internal marks cannot be negative";
    public static final String PRACTICAL_MARKS_MIN = "Practical marks cannot be negative";
    public static final String PROJECT_MARKS_MIN = "Project marks cannot be negative";
    public static final String ORAL_MARKS_MIN = "Oral marks cannot be negative";
    public static final String TOTAL_MARKS_REQUIRED = "Total marks is required";
    public static final String TOTAL_MARKS_MIN = "Total marks cannot be negative";
    public static final String PASSING_MARKS_REQUIRED = "Passing marks is required";
    public static final String PASSING_MARKS_MIN = "Passing marks cannot be negative";
}