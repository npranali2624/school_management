package com.example.school_management.dto;

import com.example.school_management.enums.Standard;
import com.example.school_management.enums.SubjectType;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResponseDto {

    private Long id;
    private String subjectName;
    private Integer subjectCode;
    private Standard standard;
    private Integer weeklyHours;
    private SubjectType subjectType;
    private Long subjectTeacherId;
    private String subjectTeacherName;
    private Integer theoryMarks;
    private Integer internalMarks;
    private Integer practicalMarks;
    private Integer projectMarks;
    private Integer oralMarks;
    private Integer totalMarks;
    private Integer passingMarks;
}