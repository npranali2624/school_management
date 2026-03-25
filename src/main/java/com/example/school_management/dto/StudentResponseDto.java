package com.example.school_management.dto;

import com.example.school_management.enums.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentResponseDto {

    private Long id;
    private String fullName;        // computed from getFullName()
    private String rollNumber;
    private String aadharNo;
    private Gender gender;
    private LocalDate dob;
    private BloodGroup bloodGroup;
    private String nationality;
    private Religion religion;
    private Category category;

    private String fatherFullName;  // computed
    private String motherFullName;  // computed
    private String guardianFullName;// computed
    private String mobilePrimary;
    private String mobileAlternate;
    private String email;

    private String fullAddress;     // computed
    private String city;
    private String state;

    private String previousSchool;
    private Double previousPercentage;

    private boolean isActive;
}