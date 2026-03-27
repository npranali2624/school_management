package com.example.school_management.dto;

import com.example.school_management.enums.Standard;
import com.example.school_management.enums.SubjectType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequestDto {

    @NotBlank(message = "Subject name is required")
    private String subjectName;

    private Integer subjectCode;

    private Standard standard;

    @NotNull(message = "Weekly hours is required")
    @Min(value = 1, message = "Weekly hours must be at least 1")
    private Integer weeklyHours;

    @NotNull(message = "Subject type is required")
    private SubjectType subjectType;

    private String subjectTeacher;

    @Min(value = 0, message = "Theory marks cannot be negative")
    private Integer theoryMarks;

    @Min(value = 0, message = "Internal marks cannot be negative")
    private Integer internalMarks;

    @Min(value = 0, message = "Practical marks cannot be negative")
    private Integer practicalMarks;

    @Min(value = 0, message = "Project marks cannot be negative")
    private Integer projectMarks;

    @Min(value = 0, message = "Oral marks cannot be negative")
    private Integer oralMarks;

    @NotNull(message = "Total marks is required")
    @Min(value = 0, message = "Total marks cannot be negative")
    private Integer totalMarks;

    @NotNull(message = "Passing marks is required")
    @Min(value = 0, message = "Passing marks cannot be negative")
    private Integer passingMarks;
}