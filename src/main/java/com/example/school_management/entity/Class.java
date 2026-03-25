package com.example.school_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name = "classes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @Column(name = "std", nullable = false)
    private int std;

    @NotBlank
    @Column(name = "division", nullable = false)
    private String division;

    @Min(0)
    @Column(name = "total_students")
    private int totalStudents;

    @Min(0)
    @Column(name = "boys")
    private int boys;

    @Min(0)
    @Column(name = "girls")
    private int girls;
}