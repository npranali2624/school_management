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
    @Column(name = "standard", nullable = true, length = 20)
    private Standard standard;

    @Min(1)
    @Column(name = "weekly_hours", nullable = false)
    private Integer weeklyHours;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_type", nullable = false)
    private SubjectType subjectType;


    //
    @ManyToOne
    @JoinColumn(name = "subject_teacher_id")
    private Teacher subjectTeacher;

    @Min(0)
    @Column(name = "theory_marks")
    private Integer theoryMarks;

    @Min(0)
    @Column(name = "internal_marks")
    private Integer internalMarks;

    @Min(0)
    @Column(name = "practical_marks")
    private Integer practicalMarks;

    @Min(0)
    @Column(name = "project_marks")
    private Integer projectMarks;

    @Min(0)
    @Column(name = "oral_marks")
    private Integer oralMarks;

    @Min(0)
    @Column(name = "total_marks", nullable = false)
    private Integer totalMarks;

    @Min(0)
    @Column(name = "passing_marks", nullable = false)
    private Integer passingMarks;
}