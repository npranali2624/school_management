package com.example.school_management.dto;

import com.example.school_management.enums.DegreeType;
import com.example.school_management.enums.Gender;
import com.example.school_management.enums.Role;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TeacherResponseDTO {


    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobile;
    private Gender gender;
    private LocalDate dob;
    private String street;
    private String city;
    private String state;
    private String pincode;
    private DegreeType degreeType;
    private String customDegreeName;
    private LocalDate joiningDate;
    private boolean isActive;
    private String aadharPhoto;
    private String panPhoto;
    private String degreeCertificate;
    private String resignationLetter;
    private String resume;
    private Integer yearsOfExperience;
    private Role role;


    private String subject;
    private String assignedClass;
    private String previousSchool;
}