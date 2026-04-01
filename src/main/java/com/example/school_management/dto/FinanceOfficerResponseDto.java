package com.example.school_management.dto;

import com.example.school_management.enums.DegreeType;
import com.example.school_management.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FinanceOfficerResponseDto {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobile;
    private Gender gender;
    private LocalDate dob;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pincode;
    private DegreeType degreeType;
    private String customDegreeName;
    private LocalDate joiningDate;
    private String aadharNo;
    private String panNo;
    private Integer yearsOfExperience;
    private String previousOrg;
    private boolean isActive;
    private String role;
}