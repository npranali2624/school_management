package com.example.school_management.entity;

import com.example.school_management.enums.Gender;
import com.example.school_management.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public abstract class Employee extends BaseEntity
{
    @NotBlank(message = "Full name is required")
    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    @Column(name = "email", length = 30, unique = true, nullable = false)
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank
    @Column(name = "password", length = 20, nullable = false)
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must have uppercase, lowercase, digit, and special character"
    )
    private String password;

    @Column(name = "mobile", length = 10, nullable = false)
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be 10 digits")
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    private Gender gender;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "street", length = 30)
    private String street;

    @Column(name = "city", length = 30)
    private String city;

    @Column(name = "state", length = 30)
    private String state;

    @Column(name = "pincode", length = 6, nullable = false)
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
    private String pincode;

    @NotBlank(message = "Degree name is required")
    @Column(name = "degree_name", length = 150, nullable = false)
    private String degreeName;

    @NotNull(message = "Joining date is required")
    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @Column(name = "aadhar_no", length = 12, nullable = false, unique = true)
    @Pattern(regexp = "^[0-9]{12}$", message = "Aadhar number must be 12 digits")
    private String aadharNo;

    @Column(name = "pan_no", length = 10, nullable = false, unique = true)
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN format (e.g. ABCDE1234F)")
    private String panNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, nullable = false)
    @NotNull(message = "Status is required")
    private Status status;

    @NotBlank(message = "Aadhar photo path is required")
    @Column(name = "aadhar_photo", length = 100, nullable = false)
    private String aadharPhoto;

    @NotBlank(message = "PAN photo path is required")
    @Column(name = "pan_photo", length = 100, nullable = false)
    private String panPhoto;

    @NotBlank(message = "Degree certificate path is required")
    @Column(name = "degree_certificate", length = 100, nullable = false)
    private String degreeCertificate;

    @Column(name = "resignation_letter", length = 100)
    private String resignationLetter;

    @NotBlank(message = "Resume path is required")
    @Column(name = "resume", length = 100, nullable = false)
    private String resume;

    @PositiveOrZero
    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;
}