package com.example.school_management.entity;

import com.example.school_management.enums.BloodGroup;
import com.example.school_management.enums.Category;
import com.example.school_management.enums.Gender;
import com.example.school_management.enums.Religion;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class Student extends BaseEntity {


    //  SECTION 1 — Personal Information


    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Size(max = 50, message = "Middle name must not exceed 50 characters")
    @Column(name = "middle_name", length = 50)
    private String middleName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @NotNull(message = "Gender is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10, nullable = false)
    private Gender gender;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @NotNull(message = "Blood group is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group", length = 15, nullable = false)
    private BloodGroup bloodGroup;

    @NotBlank(message = "Aadhar number is required")
    @Pattern(regexp = "^[0-9]{12}$", message = "Aadhar number must be exactly 12 digits")
    @Column(name = "aadhar_no", length = 12, nullable = false, unique = true)
    private String aadharNo;

    @NotBlank(message = "Nationality is required")
    @Size(max = 50, message = "Nationality must not exceed 50 characters")
    @Column(name = "nationality", length = 50, nullable = false)
    private String nationality;

    @NotNull(message = "Religion is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "religion", length = 30, nullable = false)
    private Religion religion;

    @NotNull(message = "Category is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "category", length = 30, nullable = false)
    private Category category;

    @NotNull(message = "Roll number is required")
    @Min(value = 1, message = "Roll number must be at least 1")
    @Column(name = "roll_number", unique = true)
    private Integer rollNumber;



    //  SECTION 2 — Parent / Guardian Information



    @NotBlank(message = "Father's first name is required")
    @Size(max = 50, message = "Father's first name must not exceed 50 characters")
    @Column(name = "father_first_name", length = 50, nullable = false)
    private String fatherFirstName;

    @Size(max = 50, message = "Father's middle name must not exceed 50 characters")
    @Column(name = "father_middle_name", length = 50)
    private String fatherMiddleName;

    @NotBlank(message = "Father's last name is required")
    @Size(max = 50, message = "Father's last name must not exceed 50 characters")
    @Column(name = "father_last_name", length = 50, nullable = false)
    private String fatherLastName;

    @Size(max = 100, message = "Father's occupation must not exceed 100 characters")
    @Column(name = "father_occupation", length = 100)
    private String fatherOccupation;

     //mother

    @NotBlank(message = "Mother's first name is required")
    @Size(max = 50, message = "Mother's first name must not exceed 50 characters")
    @Column(name = "mother_first_name", length = 50, nullable = false)
    private String motherFirstName;

    @Size(max = 50, message = "Mother's middle name must not exceed 50 characters")
    @Column(name = "mother_middle_name", length = 50)
    private String motherMiddleName;

    @NotBlank(message = "Mother's last name is required")
    @Size(max = 50, message = "Mother's last name must not exceed 50 characters")
    @Column(name = "mother_last_name", length = 50, nullable = false)
    private String motherLastName;

    @Size(max = 100, message = "Mother's occupation must not exceed 100 characters")
    @Column(name = "mother_occupation", length = 100)
    private String motherOccupation;

// ── Guardian (if different from Father/Mother) ──

    @Size(max = 50, message = "Guardian's first name must not exceed 50 characters")
    @Column(name = "guardian_first_name", length = 50)
    private String guardianFirstName;

    @Size(max = 50, message = "Guardian's middle name must not exceed 50 characters")
    @Column(name = "guardian_middle_name", length = 50)
    private String guardianMiddleName;

    @Size(max = 50, message = "Guardian's last name must not exceed 50 characters")
    @Column(name = "guardian_last_name", length = 50)
    private String guardianLastName;

    @Size(max = 50, message = "Guardian relation must not exceed 50 characters")
    @Column(name = "guardian_relation", length = 50)
    private String guardianRelation;

    @NotBlank(message = "Primary mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Primary mobile must be exactly 10 digits")
    @Column(name = "mobile_primary", length = 10, nullable = false)
    private String mobilePrimary;

    @Pattern(regexp = "^[0-9]{10}$", message = "Alternate mobile must be exactly 10 digits")
    @Column(name = "mobile_alternate", length = 10)
    private String mobileAlternate;

    @Email(message = "Please provide a valid email address")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Column(name = "email", length = 100, unique = true)
    private String email;

    @NotBlank(message = "Address line 1 is required")
    @Size(max = 100, message = "Address line 1 must not exceed 100 characters")
    @Column(name = "address_line1", length = 100, nullable = false)
    private String addressLine1;


    //  SECTION 3 — Academic Information


    @Size(max = 150, message = "Previous school name must not exceed 150 characters")
    @Column(name = "previous_school", length = 150)
    private String previousSchool;


    @DecimalMin(value = "0.0", message = "Percentage cannot be negative")
    @DecimalMax(value = "100.0", message = "Percentage cannot exceed 100")
    @Column(name = "previous_percentage")
    private Double previousPercentage;


    //  SECTION 4 — Document URLs


    @NotBlank(message = "Birth certificate is required")
    @Size(max = 200)
    @Column(name = "birth_certificate_url", length = 200, nullable = false)
    private String birthCertificateUrl;

    @NotBlank(message = "Aadhar photo is required")
    @Size(max = 200)
    @Column(name = "aadhar_photo_url", length = 200, nullable = false)
    private String aadharPhotoUrl;


    @Size(max = 200)
    @Column(name = "previous_marksheet_url", length = 200)
    private String previousMarksheetUrl;

    @NotBlank(message = "Passport-size photo is required")
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


    //  SECTION 5 — System Fields


    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}