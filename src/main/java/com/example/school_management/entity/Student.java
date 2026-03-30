package com.example.school_management.entity;

import com.example.school_management.constants.ValidationMessages;
import com.example.school_management.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@Entity
@Table(name = "students")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class Student extends BaseEntity {

    // SECTION 1 — Personal Information

    @NotBlank(message = ValidationMessages.FIRST_NAME_REQUIRED)
    @Size(max = 50, message = ValidationMessages.FIRST_NAME_MAX)
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Size(max = 50, message = ValidationMessages.MIDDLE_NAME_MAX)
    @Column(name = "middle_name", length = 50)
    private String middleName;

    @NotBlank(message = ValidationMessages.LAST_NAME_REQUIRED)
    @Size(max = 50, message = ValidationMessages.LAST_NAME_MAX)
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @NotNull(message = ValidationMessages.GENDER_REQUIRED)
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10, nullable = false)
    private Gender gender;

    @NotNull(message = ValidationMessages.DOB_REQUIRED)
    @Past(message = ValidationMessages.DOB_PAST)
    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @NotNull(message = ValidationMessages.BLOOD_GROUP_REQUIRED)
    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group", length = 15, nullable = false)
    private BloodGroup bloodGroup;

    @NotBlank(message = ValidationMessages.AADHAR_REQUIRED)
    @Pattern(regexp = "^[0-9]{12}$", message = ValidationMessages.AADHAR_PATTERN)
    @Column(name = "aadhar_no", length = 12, nullable = false, unique = true)
    private String aadharNo;

    @NotBlank(message = ValidationMessages.NATIONALITY_REQUIRED)
    @Size(max = 50, message = ValidationMessages.NATIONALITY_MAX)
    @Column(name = "nationality", length = 50, nullable = false)
    private String nationality;

    @NotNull(message = ValidationMessages.RELIGION_REQUIRED)
    @Enumerated(EnumType.STRING)
    @Column(name = "religion", length = 30, nullable = false)
    private Religion religion;

    @NotNull(message = ValidationMessages.CATEGORY_REQUIRED)
    @Enumerated(EnumType.STRING)
    @Column(name = "category", length = 30, nullable = false)
    private Category category;

    @NotNull(message = ValidationMessages.ROLL_NUMBER_REQUIRED)
    @Min(value = 1, message = ValidationMessages.ROLL_NUMBER_MIN)
    @Column(name = "roll_number", unique = true)
    private Integer rollNumber;

    // SECTION 2 — Parent / Guardian Information

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    // SECTION 3 — Academic Information

    @Size(max = 150, message = ValidationMessages.PREVIOUS_SCHOOL_MAX)
    @Column(name = "previous_school", length = 150)
    private String previousSchool;

    @DecimalMin(value = "0.0", message = ValidationMessages.PERCENTAGE_MIN)
    @DecimalMax(value = "100.0", message = ValidationMessages.PERCENTAGE_MAX)
    @Column(name = "previous_percentage")
    private Double previousPercentage;

    // SECTION 4 — Document URLs

    @NotBlank(message = ValidationMessages.BIRTH_CERTIFICATE_REQUIRED)
    @Size(max = 200)
    @Column(name = "birth_certificate_url", length = 200, nullable = false)
    private String birthCertificateUrl;

    @NotBlank(message = ValidationMessages.AADHAR_PHOTO_REQUIRED)
    @Size(max = 200)
    @Column(name = "aadhar_photo_url", length = 200, nullable = false)
    private String aadharPhotoUrl;

    @Size(max = 200)
    @Column(name = "previous_marksheet_url", length = 200)
    private String previousMarksheetUrl;

    @NotBlank(message = ValidationMessages.PASSPORT_PHOTO_REQUIRED)
    @Size(max = 200)
    @Column(name = "passport_photo_url", length = 200, nullable = false)
    private String passportPhotoUrl;

    @Size(max = 200)
    @Column(name = "lc_url", length = 200)
    private String leavingCertificateUrl;

    @Size(max = 200)
    @Column(name = "caste_certificate_url", length = 200)
    private String casteCertificateUrl;

    @Size(max = 200)
    @Column(name = "income_certificate_url", length = 200)
    private String incomeCertificateUrl;

    // SECTION 5 — System Fields

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}