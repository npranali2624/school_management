package com.example.school_management.service.Impl;

import com.example.school_management.entity.Parent;
import com.example.school_management.repo.ParentRepository;
import com.example.school_management.service.ParentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Parent createParent(Parent parent) {
        return parentRepository.save(parent);
    }

    @Override
    public Parent getParentById(Long id) {
        return parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found with id: " + id));
    }

    @Override
    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    @Override
    public Parent updateParent(Long id, Parent parent) {

        Parent existing = getParentById(id);

        existing.setFatherFirstName(parent.getFatherFirstName());
        existing.setFatherLastName(parent.getFatherLastName());
        existing.setMotherFirstName(parent.getMotherFirstName());
        existing.setMotherLastName(parent.getMotherLastName());
        existing.setMobilePrimary(parent.getMobilePrimary());
        existing.setEmail(parent.getEmail());
        existing.setAddressLine1(parent.getAddressLine1());
        parent.setPassword(passwordEncoder.encode(parent.getPassword()));


        return parentRepository.save(existing);
    }

    @Override
    public void deleteParent(Long id) {
        parentRepository.deleteById(id);
    }
}