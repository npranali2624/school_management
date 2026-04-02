package com.example.school_management.service;

import com.example.school_management.dto.AdminRequestDto;
import com.example.school_management.dto.AdminResponseDto;

import java.util.List;

public interface AdminService {
    AdminResponseDto createAdmin(AdminRequestDto request);
    List<AdminResponseDto> getAllAdmins();
    AdminResponseDto getAdminById(Long id);
}