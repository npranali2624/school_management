package com.example.school_management.service.Impl;

import com.example.school_management.dto.AdminRequestDto;
import com.example.school_management.dto.AdminResponseDto;
import com.example.school_management.entity.Admin;
import com.example.school_management.repo.AdminRepository;
import com.example.school_management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public AdminResponseDto createAdmin(AdminRequestDto request) {

        if (adminRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Admin admin = Admin.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(true)
                .build();

        Admin saved = adminRepository.save(admin);

        return AdminResponseDto.builder()
                .id(saved.getId())
                .username(saved.getUsername())
                .isActive(saved.isActive())
                .build();
    }

    @Override
    public List<AdminResponseDto> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(admin -> AdminResponseDto.builder()
                        .id(admin.getId())
                        .username(admin.getUsername())
                        .isActive(admin.isActive())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public AdminResponseDto getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        return AdminResponseDto.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .isActive(admin.isActive())
                .build();
    }
}