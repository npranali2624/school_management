package com.example.school_management.Mapper;

import com.example.school_management.dto.AdminRequestDto;
import com.example.school_management.dto.AdminResponseDto;
import com.example.school_management.entity.Admin;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdminMapper {

    // AdminRequestDto → Admin entity
    public Admin toEntity(AdminRequestDto dto) {
        Admin admin = new Admin();
        admin.setUsername(dto.getUsername());
        admin.setPassword(dto.getPassword()); // encode before calling this
        return admin;
    }

    // Admin entity → AdminResponseDto
    public AdminResponseDto toResponseDto(Admin admin) {
        return AdminResponseDto.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .isActive(admin.isActive())
                .build();
    }

    // List<Admin> → List<AdminResponseDto>
    public List<AdminResponseDto> toResponseDtoList(List<Admin> admins) {
        return admins.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}