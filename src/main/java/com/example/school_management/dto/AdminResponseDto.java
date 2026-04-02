package com.example.school_management.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminResponseDto {
    private Long id;
    private String username;
    private boolean isActive;
}
