package com.example.school_management.dto;

import com.example.school_management.enums.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentResponseDto {

    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;

    private Integer rollNumber;
    private String aadharNo;
    private Gender gender;
    private LocalDate dob;
    private BloodGroup bloodGroup;
    private String nationality;
    private Religion religion;
    private Category category;

    private String fatherFirstName;
    private String fatherMiddleName;
    private String fatherLastName;
    private String fatherOccupation;

    private String motherFirstName;
    private String motherMiddleName;
    private String motherLastName;
    private String motherOccupation;

    private String guardianFirstName;
    private String guardianMiddleName;
    private String guardianLastName;
    private String guardianRelation;

    private String mobilePrimary;
    private String mobileAlternate;
    private String email;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String password;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pincode;


    private String previousSchool;
    private Double previousPercentage;

    private boolean isActive;


}