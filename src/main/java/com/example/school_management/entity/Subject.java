package com.example.school_management.entity;

import com.example.school_management.enums.Standard;
import com.example.school_management.enums.SubjectType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "subject_code", unique = true)
    private Integer subjectCode;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "standard", nullable = false, length = 10)
    private Standard standard;

    // How many hours/periods this subject has per week
    @Min(1)
    @Column(name = "weekly_hours", nullable = false)
    private Integer weeklyHours;

    // Is this subject compulsory or optional?
    @Enumerated(EnumType.STRING)
    @Column(name = "subject_type", nullable = false)
    private SubjectType subjectType;

    @Column(name = "subject_teacher")
    private String subjectTeacher;

    // Written theory exam marks (e.g. 60)
    @Min(0)
    @Column(name = "theory_marks")
    private Integer theoryMarks;

    // Internal assessment marks (e.g. 10)
    @Min(0)
    @Column(name = "internal_marks")
    private Integer internalMarks;

    // Practical/lab marks (e.g. 20) - applicable for Science, Computer etc.
    @Min(0)
    @Column(name = "practical_marks")
    private Integer practicalMarks;

    // Project/assignment marks (e.g. 10)
    @Min(0)
    @Column(name = "project_marks")
    private Integer projectMarks;

    // Oral/viva marks (e.g. 10) - applicable for languages etc.
    @Min(0)
    @Column(name = "oral_marks")
    private Integer oralMarks;

    // Total marks (e.g. 100) - sum of all above
    @Min(0)
    @Column(name = "total_marks", nullable = false)
    private Integer totalMarks;

    // Passing marks (e.g. 35)
    @Min(0)
    @Column(name = "passing_marks", nullable = false)
    private Integer passingMarks;
}