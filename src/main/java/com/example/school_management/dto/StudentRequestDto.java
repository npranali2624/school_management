package com.example.school_management.dto;

import com.example.school_management.constants.ValidationMessages;
import com.example.school_management.enums.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentRequestDto {

    // Personal Info
    @NotBlank(message = ValidationMessages.FIRST_NAME_REQUIRED)
    private String firstName;

    private String middleName;

    @NotBlank(message = ValidationMessages.LAST_NAME_REQUIRED)
    private String lastName;

    @NotNull(message = ValidationMessages.GENDER_REQUIRED)
    private Gender gender;

    @NotNull(message = ValidationMessages.DOB_REQUIRED)
    @Past(message = ValidationMessages.DOB_PAST)
    private LocalDate dob;

    @NotNull(message = ValidationMessages.BLOOD_GROUP_REQUIRED)
    private BloodGroup bloodGroup;

    @NotNull(message = ValidationMessages.ROLL_NUMBER_REQUIRED)
    @Min(value = 1, message = ValidationMessages.ROLL_NUMBER_MIN)
    private Integer rollNumber;

    @NotBlank(message = ValidationMessages.AADHAR_REQUIRED)
    @Pattern(regexp = "^[0-9]{12}$", message = ValidationMessages.AADHAR_PATTERN)
    private String aadharNo;

    @NotBlank(message = ValidationMessages.NATIONALITY_REQUIRED)
    private String nationality;

    @NotNull(message = ValidationMessages.RELIGION_REQUIRED)
    private Religion religion;

    @NotNull(message = ValidationMessages.CATEGORY_REQUIRED)
    private Category category;

    // Parent Info
    @NotBlank(message = ValidationMessages.FATHER_FIRST_NAME_REQUIRED)
    private String fatherFirstName;
    private String fatherMiddleName;

    @NotBlank(message = ValidationMessages.FATHER_LAST_NAME_REQUIRED)
    private String fatherLastName;
    private String fatherOccupation;

    @NotBlank(message = ValidationMessages.MOTHER_FIRST_NAME_REQUIRED)
    private String motherFirstName;
    private String motherMiddleName;

    @NotBlank(message = ValidationMessages.MOTHER_LAST_NAME_REQUIRED)
    private String motherLastName;
    private String motherOccupation;

    private String guardianFirstName;
    private String guardianMiddleName;
    private String guardianLastName;
    private String guardianRelation;

    // Contact
    @NotBlank(message = ValidationMessages.MOBILE_PRIMARY_REQUIRED)
    @Pattern(regexp = "^[0-9]{10}$", message = ValidationMessages.MOBILE_PRIMARY_PATTERN)
    private String mobilePrimary;

    @Pattern(regexp = "^[0-9]{10}$", message = ValidationMessages.MOBILE_ALTERNATE_PATTERN)
    private String mobileAlternate;

    @Email(message = ValidationMessages.EMAIL_INVALID)
    private String email;

    private String password;

    // Address
    @NotBlank(message = ValidationMessages.ADDRESS_LINE1_REQUIRED)
    private String addressLine1;
    private String addressLine2;

    @NotBlank(message = ValidationMessages.CITY_REQUIRED)
    private String city;

    @NotBlank(message = ValidationMessages.STATE_REQUIRED)
    private String state;

    @NotBlank(message = ValidationMessages.PINCODE_REQUIRED)
    @Pattern(regexp = "^[0-9]{6}$", message = ValidationMessages.PINCODE_PATTERN)
    private String pincode;

    // Academic
    private String previousSchool;

    @DecimalMin(value = "0.0", message = ValidationMessages.PERCENTAGE_MIN)
    @DecimalMax(value = "100.0", message = ValidationMessages.PERCENTAGE_MAX)
    private Double previousPercentage;

    // Documents
    @NotBlank(message = ValidationMessages.BIRTH_CERTIFICATE_REQUIRED)
    private String birthCertificateUrl;

    @NotBlank(message = ValidationMessages.AADHAR_PHOTO_REQUIRED)
    private String aadharPhotoUrl;

    private String previousMarksheetUrl;

    @NotBlank(message = ValidationMessages.PASSPORT_PHOTO_REQUIRED)
    private String passportPhotoUrl;

    private String leavingCertificateUrl;
    private String casteCertificateUrl;
    private String incomeCertificateUrl;
}