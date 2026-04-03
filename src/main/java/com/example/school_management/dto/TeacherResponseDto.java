package com.example.school_management.dto;

import com.example.school_management.enums.DegreeType;
import com.example.school_management.enums.Gender;
import com.example.school_management.enums.Role;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class TeacherResponseDto {

    private Long id;
    private Instant createdAt;
    private Instant updatedAt;

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
    private String aadharNo;
    private String panNo;
    private DegreeType degreeType;
    private String customDegreeName;
    private LocalDate joiningDate;
    private Integer yearsOfExperience;
    private boolean isActive;
    private Role role;
    private String previousSchool;

    private String aadharPhotourl;
    private String panPhotourl;
    private String degreeCertificateurl;
    private String resignationLetterurl;
    private String resumeurl;

    private Long subjectId;
    private String subjectName;

    private Long specializationId;
    private String specializationName;

    private Long assignedClassId;
    private String assignedClassName;
}