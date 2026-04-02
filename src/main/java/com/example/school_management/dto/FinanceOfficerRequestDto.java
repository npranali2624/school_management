package com.example.school_management.dto;

import com.example.school_management.constants.ValidationMessages;
import com.example.school_management.enums.DegreeType;
import com.example.school_management.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FinanceOfficerRequestDto {


    @NotBlank(message = ValidationMessages.FIRST_NAME_REQUIRED)
    @Size(max = 50, message = ValidationMessages.FIRST_NAME_MAX)
    private String firstName;

    @Size(max = 50, message = ValidationMessages.MIDDLE_NAME_MAX)
    private String middleName;

    @NotBlank(message = ValidationMessages.LAST_NAME_REQUIRED)
    @Size(max = 50, message = ValidationMessages.LAST_NAME_MAX)
    private String lastName;


    @NotBlank(message = ValidationMessages.EMAIL_REQUIRED)
    @Email(message = ValidationMessages.EMAIL_INVALID)
    @Size(max = 50)
    private String email;

    @NotBlank(message = ValidationMessages.PASSWORD_REQUIRED)
    @Size(min = 6, message = ValidationMessages.PASSWORD_MIN)
    private String password;

    @NotBlank(message = ValidationMessages.MOBILE_REQUIRED)
    @Pattern(regexp = "^[0-9]{10}$", message = ValidationMessages.MOBILE_PATTERN)
    private String mobile;



    @NotNull(message = ValidationMessages.GENDER_REQUIRED)
    private Gender gender;


    @NotNull(message = ValidationMessages.DOB_REQUIRED)
    private LocalDate dob;


    @NotBlank(message = ValidationMessages.ADDRESS_LINE1_REQUIRED)
    @Size(max = 100)
    private String addressLine1;

    @Size(max = 100)
    private String addressLine2;

    @Size(max = 50)
    private String city;

    @Size(max = 50)
    private String state;

    @NotBlank(message = ValidationMessages.PINCODE_REQUIRED)
    @Pattern(regexp = "^[0-9]{6}$", message = ValidationMessages.PINCODE_PATTERN)
    private String pincode;


    @NotNull(message = ValidationMessages.DEGREE_TYPE_REQUIRED)
    private DegreeType degreeType;

    @Size(max = 150)
    private String customDegreeName;


    @NotNull(message = ValidationMessages.JOINING_DATE_REQUIRED)
    private LocalDate joiningDate;

    @NotNull(message = ValidationMessages.EXPERIENCE_REQUIRED)
    @PositiveOrZero(message = ValidationMessages.EXPERIENCE_POSITIVE)
    private Integer yearsOfExperience;

    @NotBlank(message = ValidationMessages.AADHAR_REQUIRED)
    @Pattern(regexp = "^[0-9]{12}$", message = ValidationMessages.AADHAR_PATTERN)
    private String aadharNo;

    @NotBlank(message = ValidationMessages.PAN_REQUIRED)
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = ValidationMessages.PAN_PATTERN)
    private String panNo;


    @Size(max = 200)
    private String aadharPhotourl;

    @Size(max = 200)
    private String panPhotourl;

    @Size(max = 200)
    private String degreeCertificateurl;

    @Size(max = 200)
    private String resignationLetterurl;

    @Size(max = 200)
    private String resumeurl;

    @Size(max = 100, message = ValidationMessages.PREVIOUS_ORG_MAX)
    private String previousOrg;
}