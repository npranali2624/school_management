package com.example.school_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parents")
@Data                    // ← generates all getters + setters
@NoArgsConstructor       // ← generates no-args constructor
@AllArgsConstructor      // ← generates all-args constructor
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ── Father ──
    @NotBlank(message = "Father's first name is required")
    @Size(max = 50)
    @Column(name = "father_first_name", length = 50, nullable = false)
    private String fatherFirstName;

    @Size(max = 50)
    @Column(name = "father_middle_name", length = 50)
    private String fatherMiddleName;

    @NotBlank(message = "Father's last name is required")
    @Size(max = 50)
    @Column(name = "father_last_name", length = 50, nullable = false)
    private String fatherLastName;

    @Size(max = 100)
    @Column(name = "father_occupation", length = 100)
    private String fatherOccupation;

    // ── Mother ──
    @NotBlank(message = "Mother's first name is required")
    @Size(max = 50)
    @Column(name = "mother_first_name", length = 50, nullable = false)
    private String motherFirstName;

    @Size(max = 50)
    @Column(name = "mother_middle_name", length = 50)
    private String motherMiddleName;

    @NotBlank(message = "Mother's last name is required")
    @Size(max = 50)
    @Column(name = "mother_last_name", length = 50, nullable = false)
    private String motherLastName;

    @Size(max = 100)
    @Column(name = "mother_occupation", length = 100)
    private String motherOccupation;

    // ── Guardian ──
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

    // ── Contact ──
    @NotBlank(message = "Primary mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Primary mobile must be exactly 10 digits")
    @Column(name = "mobile_primary", length = 10, nullable = false)
    private String mobilePrimary;

    @Pattern(regexp = "^[0-9]{10}$", message = "Alternate mobile must be exactly 10 digits")
    @Column(name = "mobile_alternate", length = 10)
    private String mobileAlternate;

    @Email(message = "Please provide a valid email address")
    @Size(max = 100)
    @Column(name = "email", length = 100, unique = true)
    private String email;


    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Address line 1 is required")
    @Size(max = 100)
    @Column(name = "address_line1", length = 100, nullable = false)
    private String addressLine1;


    // ── Back-reference to students (optional) ──
    @JsonIgnore
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();
}