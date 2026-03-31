package com.example.school_management.entity;

import com.example.school_management.enums.DegreeType;
import com.example.school_management.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.example.school_management.enums.Role;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public abstract class Employee extends BaseEntity {

    @NotBlank
    @Size(max = 50)
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Size(max = 50)
    @Column(name = "middle_name", length = 50)
    private String middleName;

    @NotBlank
    @Size(max = 50)
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Email
    @NotBlank
    @Size(max = 50)
    @Column(unique=true ,name = "email", length = 50, nullable = false)
    private String email;

    @NotBlank
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be 10 digits")
    @Column(unique =true ,name = "mobile", length = 10, nullable = false)
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    private Gender gender;

    @Column(name = "dob")
    private LocalDate dob;

    @Size(max = 100)
    @Column(name = "address_line1", length = 100, nullable = false)
    private String addressLine1;

    @Size(max = 100)
    @Column(name = "address_line2", length = 100)
    private String addressLine2;

    @Size(max = 50)
    @Column(name = "city", length = 50)
    private String city;

    @Size(max = 50)
    @Column(name = "state", length = 50)
    private String state;

    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
    @Column(name = "pincode", length = 6, nullable = false)
    private String pincode;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree_type", length = 50, nullable = false)
    private DegreeType degreeType;


    @Column(name = "custom_degree_name", length = 150)
    private String customDegreeName;

    @NotNull
    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @Pattern(regexp = "^[0-9]{12}$", message = "Aadhar must be 12 digits")
    @Column(name = "aadhar_no", length = 12, nullable = false)
    private String aadharNo;

    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN format")
    @Column(name = "pan_no", length = 10, nullable = false)
    private String panNo;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Size(max = 200)
    @Column(name = "aadhar_photo_url", length = 200)
    private String aadharPhotourl;


    @Size(max = 200)
    @Column(name = "pan_photo_url", length = 200)
    private String panPhotourl;


    @Size(max = 200)
    @Column(name = "degree_certificate_url", length = 200)
    private String degreeCertificateurl;

    @Size(max = 200)
    @Column(name = "resignation_letter_url", length = 200)
    private String resignationLetterurl;


    @Size(max = 200)
    @Column(name = "resume_url", length = 200)
    private String resumeurl;

    @PositiveOrZero
    @Max(50)
    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
}