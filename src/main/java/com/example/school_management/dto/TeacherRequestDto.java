package com.example.school_management.dto;

import com.example.school_management.enums.DegreeType;
import com.example.school_management.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TeacherRequestDto {


    @NotBlank(message = "First name is required")
    @Size(max = 20)
    private String firstName;

    @NotBlank(message = "Middle name is required")
    @Size(max = 20)
    private String middleName;

    @NotBlank(message = "Last name is required")
    @Size(max = 20)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 30)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must have uppercase, lowercase, digit, and special character"
    )
    private String password;

    @NotBlank(message = "Mobile is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be 10 digits")
    private String mobile;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Date of birth is required")
    private LocalDate dob;

    @NotBlank(message = "Street is required")
    @Size(max = 30)
    private String street;

    @NotBlank(message = "City is required")
    @Size(max = 30)
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 30)
    private String state;

    @NotBlank(message = "Pincode is required")
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
    private String pincode;

    @NotNull(message = "Degree type is required")
    private DegreeType degreeType;

    // Required only when degreeType is OTHER
    @Size(max = 150, message = "Custom degree name must not exceed 150 characters")
    private String customDegreeName;

    @NotNull(message = "Joining date is required")
    private LocalDate joiningDate;

    @NotBlank(message = "Aadhar number is required")
    @Pattern(regexp = "^[0-9]{12}$", message = "Aadhar number must be 12 digits")
    private String aadharNo;

    @NotBlank(message = "PAN number is required")
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN format")
    private String panNo;

    @NotBlank(message = "Aadhar photo URL is required")
    private String aadharPhoto;

    @NotBlank(message = "PAN photo URL is required")
    private String panPhoto;

    @NotBlank(message = "Degree certificate URL is required")
    private String degreeCertificate;

    @NotBlank(message = "Resignation letter URL is required")
    private String resignationLetter;

    @NotBlank(message = "Resume URL is required")
    private String resume;

    @NotNull(message = "Years of experience is required")
    @PositiveOrZero(message = "Years of experience must be zero or positive")
    private Integer yearsOfExperience;

    // ---------- Teacher-Specific Fields ----------

    @NotBlank(message = "Subject is required")
    @Size(max = 50)
    private String subject;

    @NotBlank(message = "Assigned class is required")
    @Size(max = 20)
    private String assignedClass;

    @NotBlank(message = "Previous school is required")
    @Size(max = 100)
    private String previousSchool;
}
