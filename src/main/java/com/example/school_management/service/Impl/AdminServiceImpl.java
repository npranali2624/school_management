package com.example.school_management.service.Impl;

import com.example.school_management.Mapper.AdminMapper;
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
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AdminResponseDto createAdmin(AdminRequestDto request) {
        if (adminRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Admin admin = adminMapper.toEntity(request);
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setActive(true);

        return adminMapper.toResponseDto(adminRepository.save(admin));
    }

    @Override
    public List<AdminResponseDto> getAllAdmins() {
        return adminMapper.toResponseDtoList(adminRepository.findAll());
    }

    @Override
    public AdminResponseDto getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return adminMapper.toResponseDto(admin);
    }
}