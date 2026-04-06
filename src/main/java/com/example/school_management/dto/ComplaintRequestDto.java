package com.example.school_management.dto;

import com.example.school_management.constants.ValidationMessages;
import com.example.school_management.enums.ComplaintCategory;
import com.example.school_management.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ComplaintRequestDto {

    @NotBlank(message = ValidationMessages.COMPLAINT_TITLE_REQUIRED)
    @Size(max = 150, message = ValidationMessages.COMPLAINT_TITLE_MAX)
    private String title;

    @NotBlank(message = ValidationMessages.COMPLAINT_DESCRIPTION_REQUIRED)
    @Size(max = 1000, message = ValidationMessages.COMPLAINT_DESCRIPTION_MAX)
    private String description;

    @NotNull(message = ValidationMessages.COMPLAINT_CATEGORY_REQUIRED)
    private ComplaintCategory category;

    private Priority priority;

    @Size(max = 500, message = ValidationMessages.COMPLAINT_MEDIA_MAX)
    private List<String> supportedMedia;

    private Long studentId;
    private Long teacherId;
}