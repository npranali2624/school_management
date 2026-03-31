package com.example.school_management.entity;

import com.example.school_management.enums.AssignmentMarkingType;
import com.example.school_management.enums.AssignmentStatus;
import com.example.school_management.enums.AssignmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import static com.example.school_management.constants.ValidationMessages.*;

@Entity
@Table(name = "assignments")
@Data
@SuperBuilder
@NoArgsConstructor
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @NotBlank(message = ASSIGNMENT_TITLE_REQUIRED)
    @Size(max = 255, message = ASSIGNMENT_TITLE_MAX)
    @Column(name = "title", nullable = false)
    private String title;

    @Size(max = 2000, message = ASSIGNMENT_DESCRIPTION_MAX)
    @Column(name = "description", length = 2000)
    private String description;

    @NotNull(message = ASSIGNMENT_TYPE_REQUIRED)
    @Enumerated(EnumType.STRING)
    @Column(name = "assignment_type", nullable = false, length = 20)
    private AssignmentType assignmentType;

    @NotNull(message = MARKING_TYPE_REQUIRED)
    @Enumerated(EnumType.STRING)
    @Column(name = "marking_type", nullable = false, length = 20)
    private AssignmentMarkingType markingType;

    @Min(value = 0, message = ASSIGNMENT_TOTAL_MARKS_MIN)
    @Column(name = "total_marks")
    private Integer totalMarks;

    @Min(value = 0, message = ASSIGNMENT_PASSING_MARKS_MIN)
    @Column(name = "passing_marks")
    private Integer passingMarks;



    @Column(name = "assigned_date", nullable = false, updatable = false)
    private LocalDateTime assignedDate;

    @NotNull(message = DUE_DATE_REQUIRED)
    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private AssignmentStatus status;



    @Column(name = "allow_late_submission", nullable = false)
    private Boolean allowLateSubmission = false;

    @Column(name = "late_submission_due_date")
    private LocalDateTime lateSubmissionDueDate;


    @Size(max = 500, message = ALLOWED_FILE_TYPES_MAX)
    @Column(name = "allowed_file_types", length = 500)
    private String allowedFileTypes;


    @NotNull(message = CLASS_REQUIRED)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private Class assignedClass;

    @NotNull(message = TEACHER_REQUIRED)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @NotNull(message = SUBJECT_REQUIRED_ASSIGNMENT)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
}