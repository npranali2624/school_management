package com.example.school_management.dto;

import com.example.school_management.constants.ValidationMessages;
import com.example.school_management.entity.Subject;
import com.example.school_management.enums.DegreeType;
import com.example.school_management.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TeacherRequestDto {

    @NotBlank(message = ValidationMessages.FIRST_NAME_REQUIRED)
    @Size(max = 20)
    private String firstName;

    @NotBlank(message = ValidationMessages.MIDDLE_NAME_REQUIRED)
    @Size(max = 20)
    private String middleName;

    @NotBlank(message = ValidationMessages.LAST_NAME_REQUIRED)
    @Size(max = 20)
    private String lastName;

    @NotBlank(message = ValidationMessages.EMAIL_REQUIRED)
    @Email(message = ValidationMessages.EMAIL_FORMAT_INVALID)
    @Size(max = 30)
    private String email;

    @NotBlank(message = ValidationMessages.PASSWORD_REQUIRED)
    @Size(min = 8, max = 20, message = ValidationMessages.PASSWORD_SIZE)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = ValidationMessages.PASSWORD_PATTERN
    )
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

    @NotBlank(message = ValidationMessages.CITY_REQUIRED)
    @Size(max = 30)
    private String city;

    @NotBlank(message = ValidationMessages.STATE_REQUIRED)
    @Size(max = 30)
    private String state;

    @NotBlank(message = ValidationMessages.PINCODE_REQUIRED)
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
    private String pincode;

    @NotBlank(message = ValidationMessages.AADHAR_REQUIRED)
    @Pattern(regexp = "^[0-9]{12}$", message = ValidationMessages.AADHAR_PATTERN)

    @NotBlank(message = ValidationMessages.PINCODE_REQUIRED)
    @Pattern(regexp = "^[0-9]{6}$", message = ValidationMessages.PINCODE_PATTERN)

    @NotNull(message = ValidationMessages.DEGREE_TYPE_REQUIRED)
    private DegreeType degreeType;

    @Size(max = 150, message = ValidationMessages.CUSTOM_DEGREE_MAX)
    private String customDegreeName;

    @NotNull(message = ValidationMessages.JOINING_DATE_REQUIRED)
    private LocalDate joiningDate;


    @NotBlank(message = ValidationMessages.PAN_REQUIRED)
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = ValidationMessages.PAN_PATTERN)
    private String panNo;

    private String aadharPhoto;


    private String panPhoto;


    private String degreeCertificate;


    private String resignationLetter;


    private String resume;

    @NotNull(message = ValidationMessages.EXPERIENCE_REQUIRED)
    @PositiveOrZero(message = ValidationMessages.EXPERIENCE_POSITIVE)
    private Integer yearsOfExperience;


    @NotBlank(message = ValidationMessages.SUBJECT_REQUIRED)
    @Size(max = 50)
    private Subject subject;

    @NotBlank(message = ValidationMessages.ASSIGNED_CLASS_REQUIRED)
    @Size(max = 20)
    private Class assignedClass;

    @NotNull(message = ValidationMessages.SUBJECT_REQUIRED)
    private Long subjectId;

    @NotNull(message = ValidationMessages.SPECIALIZATION_REQUIRED)
    private Long specializationId;

    @NotBlank(message = ValidationMessages.PREVIOUS_SCHOOL_REQUIRED)
    @Size(max = 100)
    private String previousSchool;
}