package com.example.school_management.dto;

import com.example.school_management.enums.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentRequestDto {

    // Personal Info
    @NotBlank(message = "First name is required")
    private String firstName;
    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @NotNull(message = "Blood group is required")
    private BloodGroup bloodGroup;

    @NotNull(message = "Roll number is required")
    @Min(value = 1, message = "Roll number must be at least 1")
    private Integer rollNumber;

    @NotBlank
    @Pattern(regexp = "^[0-9]{12}$", message = "Aadhar must be 12 digits")
    private String aadharNo;

    @NotBlank
    private String nationality;

    @NotNull
    private Religion religion;

    @NotNull
    private Category category;

    // Parent Info
    @NotBlank private String fatherFirstName;
    private String fatherMiddleName;
    @NotBlank private String fatherLastName;
    private String fatherOccupation;

    @NotBlank private String motherFirstName;
    private String motherMiddleName;
    @NotBlank private String motherLastName;
    private String motherOccupation;

    private String guardianFirstName;
    private String guardianMiddleName;
    private String guardianLastName;
    private String guardianRelation;

    // Contact
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Primary mobile must be 10 digits")
    private String mobilePrimary;

    @Pattern(regexp = "^[0-9]{10}$", message = "Alternate mobile must be 10 digits")
    private String mobileAlternate;

    @Email
    private String email;

    private String password;

    // Address
    @NotBlank private String addressLine1;
    private String addressLine2;
    @NotBlank private String city;
    @NotBlank private String state;

    @NotBlank
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
    private String pincode;

    // Academic
    private String previousSchool;

    @DecimalMin("0.0") @DecimalMax("100.0")
    private Double previousPercentage;

    // Documents
    @NotBlank private String birthCertificateUrl;
    @NotBlank private String aadharPhotoUrl;
    private String previousMarksheetUrl;
    @NotBlank private String passportPhotoUrl;
    private String leavingCertificateUrl;
    private String casteCertificateUrl;
    private String incomeCertificateUrl;
}