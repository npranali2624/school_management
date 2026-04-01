package com.example.school_management.dto;

import com.example.school_management.enums.DegreeType;
import com.example.school_management.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FinanceOfficerRequestDto {

    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    private String mobile;

    private Gender gender;
    private LocalDate dob;

    @NotBlank
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;

    @Pattern(regexp = "^[0-9]{6}$")
    private String pincode;

    private DegreeType degreeType;
    private String customDegreeName;

    @NotNull
    private LocalDate joiningDate;

    @Pattern(regexp = "^[0-9]{12}$")
    private String aadharNo;

    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$")
    private String panNo;

    @PositiveOrZero
    @Max(50)
    private Integer yearsOfExperience;

    private String previousOrg;   // FinanceOfficer specific field
}