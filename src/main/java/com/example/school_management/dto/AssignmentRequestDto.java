package com.example.school_management.dto;

import com.example.school_management.enums.AssignmentMarkingType;
import com.example.school_management.enums.AssignmentType;
import jakarta.validation.constraints.*;
import static com.example.school_management.constants.ValidationMessages.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignmentRequestDto {

    @NotBlank(message = ASSIGNMENT_TITLE_REQUIRED)
    @Size(max = 255)
    private String title;

    @Size(max = 2000)
    private String description;

    @NotNull(message = ASSIGNMENT_TYPE_REQUIRED)
    private AssignmentType assignmentType;

    @NotNull(message = MARKING_TYPE_REQUIRED)
    private AssignmentMarkingType markingType;

    @Min(value = 0, message = ASSIGNMENT_TOTAL_MARKS_MIN)
    private Integer totalMarks;

    @Min(value = 0, message = ASSIGNMENT_PASSING_MARKS_MIN)
    private Integer passingMarks;

    @NotNull(message = DUE_DATE_REQUIRED)
    @Future(message = DUE_DATE_FUTURE)
    private LocalDateTime dueDate;

    private Boolean allowLateSubmission = false;

    private LocalDateTime lateSubmissionDueDate;

    @Size(max = 500, message = ALLOWED_FILE_TYPES_MAX)
    private String allowedFileTypes;

    @NotNull(message = TEACHER_REQUIRED)
    private Long teacherId;

    @NotNull(message = CLASS_REQUIRED)
    private Long classId;

    @NotNull(message = SUBJECT_REQUIRED_ASSIGNMENT)
    private Long subjectId;
}