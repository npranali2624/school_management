package com.example.school_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class Teacher extends Employee {

    @Size(max = 100)
    @Column(name = "previous_school", length = 100)
    private String previousSchool;

    // Teacher is class teacher of ONE class
    @OneToOne(mappedBy = "classTeacher")
    private Class assignedClass;


    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;


    @Builder.Default
    @OneToMany(mappedBy = "subjectTeacher")
    private List<Subject> subjects = new ArrayList<>();
}