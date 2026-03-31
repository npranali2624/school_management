package com.example.school_management.entity;

import com.example.school_management.constants.ValidationMessages;
import com.example.school_management.enums.AadharOwnerType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = ValidationMessages.FATHER_FIRST_NAME_REQUIRED)
    @Size(max = 50)
    @Column(name = "father_first_name", length = 50, nullable = false)
    private String fatherFirstName;

    @Size(max = 50)
    @Column(name = "father_middle_name", length = 50)
    private String fatherMiddleName;

    @NotBlank(message = ValidationMessages.FATHER_LAST_NAME_REQUIRED)
    @Size(max = 50)
    @Column(name = "father_last_name", length = 50, nullable = false)
    private String fatherLastName;

    @Size(max = 100)
    @Column(name = "father_occupation", length = 100)
    private String fatherOccupation;

    @NotBlank(message = ValidationMessages.MOTHER_FIRST_NAME_REQUIRED)
    @Size(max = 50)
    @Column(name = "mother_first_name", length = 50, nullable = false)
    private String motherFirstName;

    @Size(max = 50)
    @Column(name = "mother_middle_name", length = 50)
    private String motherMiddleName;

    @NotBlank(message = ValidationMessages.MOTHER_LAST_NAME_REQUIRED)
    @Size(max = 50)
    @Column(name = "mother_last_name", length = 50, nullable = false)
    private String motherLastName;

    @Size(max = 100)
    @Column(name = "mother_occupation", length = 100)
    private String motherOccupation;

    @Size(max = 50)
    @Column(name = "guardian_first_name", length = 50)
    private String guardianFirstName;

    @Size(max = 50)
    @Column(name = "guardian_middle_name", length = 50)
    private String guardianMiddleName;

    @Size(max = 50)
    @Column(name = "guardian_last_name", length = 50)
    private String guardianLastName;

    @Size(max = 50)
    @Column(name = "guardian_relation", length = 50)
    private String guardianRelation;

    @NotBlank(message = ValidationMessages.MOBILE_PRIMARY_REQUIRED)
    @Pattern(regexp = "^[0-9]{10}$", message = ValidationMessages.MOBILE_PRIMARY_PATTERN)
    @Column(name = "mobile_primary", length = 10, nullable = false)
    private String mobilePrimary;

    @Pattern(regexp = "^[0-9]{10}$", message = ValidationMessages.MOBILE_ALTERNATE_PATTERN)
    @Column(name = "mobile_alternate", length = 10)
    private String mobileAlternate;


    @Size(max = 100)
    @Column(name = "emergency_contact_name", length = 100)
    private String emergencyContactName;

    @Pattern(regexp = "^[0-9]{10}$", message = ValidationMessages.EMERGENCY_CONTACT_NUMBER_PATTERN)
    @Column(name = "emergency_contact_number", length = 10)
    private String emergencyContactNumber;


    @Email(message = ValidationMessages.EMAIL_INVALID)
    @Size(max = 100)
    @Column(name = "email", length = 100, unique = true)
    private String email;

    @NotBlank(message = ValidationMessages.PASSWORD_REQUIRED)
    @Size(min = 6, message = ValidationMessages.PASSWORD_MIN)
    @Column(name = "password", nullable = false)
    private String password;


    @NotBlank(message = ValidationMessages.ADDRESS_LINE1_REQUIRED)
    @Size(max = 100)
    @Column(name = "address_line1", length = 100, nullable = false)
    private String addressLine1;

    @Size(max = 100)
    @Column(name = "address_line2", length = 100)
    private String addressLine2;

    @NotBlank(message = ValidationMessages.CITY_REQUIRED)
    @Size(max = 50)
    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @NotBlank(message = ValidationMessages.STATE_REQUIRED)
    @Size(max = 50)
    @Column(name = "state", length = 50, nullable = false)
    private String state;

    @NotBlank(message = ValidationMessages.PINCODE_REQUIRED)
    @Pattern(regexp = "^[0-9]{6}$", message = ValidationMessages.PINCODE_PATTERN)
    @Column(name = "pincode", length = 6, nullable = false)
    private String pincode;

    @Size(max = 200)
    @Column(name = "aadhar_photo_url", length = 200)
    private String aadharPhotoUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "aadhar_owner_type", length = 20)
    private AadharOwnerType aadharOwnerType;

    @JsonIgnore
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();
}