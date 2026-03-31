package com.example.school_management.dto;

import com.example.school_management.enums.AssignmentMarkingType;
import com.example.school_management.enums.AssignmentStatus;
import com.example.school_management.enums.AssignmentType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignmentResponseDto {

    private Long id;
    private String title;
    private String description;
    private AssignmentType assignmentType;
    private AssignmentMarkingType markingType;
    private Integer totalMarks;
    private Integer passingMarks;
    private LocalDateTime assignedDate;
    private LocalDateTime dueDate;
    private AssignmentStatus status;
    private Boolean allowLateSubmission;
    private LocalDateTime lateSubmissionDueDate;
    private String allowedFileTypes;

    private Long teacherId;
    private String teacherName;


    private Long classId;

    private Long subjectId;
    private String subjectName;
}