package com.example.school_management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "teachers")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class Teacher extends Employee
{
    @Column(name = "subject", length = 50)
    private String subject;

    @Column(name = "assigned_class", length = 20)
    private String assignedClass;

    @Column(name = "previous_school", length = 100)
    private String previousSchool;
}