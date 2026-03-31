
package com.example.school_management.dto;

import com.example.school_management.constants.ValidationMessages;
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

    @NotBlank(message = ValidationMessages.SUBJECT_NAME_REQUIRED)
    private String subjectName;

    private Integer subjectCode;

    private Standard standard;

    @NotNull(message = ValidationMessages.WEEKLY_HOURS_REQUIRED)
    @Min(value = 1, message = ValidationMessages.WEEKLY_HOURS_MIN)
    private Integer weeklyHours;

    @NotNull(message = ValidationMessages.SUBJECT_TYPE_REQUIRED)
    private SubjectType subjectType;

    private Long subjectTeacherId;

    @Min(value = 0, message = ValidationMessages.THEORY_MARKS_MIN)
    private Integer theoryMarks;

    @Min(value = 0, message = ValidationMessages.INTERNAL_MARKS_MIN)
    private Integer internalMarks;

    @Min(value = 0, message = ValidationMessages.PRACTICAL_MARKS_MIN)
    private Integer practicalMarks;

    @Min(value = 0, message = ValidationMessages.PROJECT_MARKS_MIN)
    private Integer projectMarks;

    @Min(value = 0, message = ValidationMessages.ORAL_MARKS_MIN)
    private Integer oralMarks;

    @NotNull(message = ValidationMessages.TOTAL_MARKS_REQUIRED)
    @Min(value = 0, message = ValidationMessages.TOTAL_MARKS_MIN)
    private Integer totalMarks;

    @NotNull(message = ValidationMessages.PASSING_MARKS_REQUIRED)
    @Min(value = 0, message = ValidationMessages.PASSING_MARKS_MIN)
    private Integer passingMarks;
}