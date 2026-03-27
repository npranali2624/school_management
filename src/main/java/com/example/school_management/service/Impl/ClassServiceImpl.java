package com.example.school_management.service.Impl;

import com.example.school_management.entity.Class;
import com.example.school_management.repo.ClassRepository;
import com.example.school_management.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    // ✅ CREATE
    @Override
    public Class createClass(Class classEntity) {

        // 🔥 FIX: Generate className before saving
        String className = classEntity.getStandard() + "-" + classEntity.getDivision();
        classEntity.setClassName(className);

        return classRepository.save(classEntity);
    }

    // ✅ GET ALL
    @Override
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    // ✅ GET BY ID
    @Override
    public Class getClassById(Long id) {
        return classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found with id: " + id));
    }

    // ✅ UPDATE
    @Override
    public Class updateClass(Long id, Class classEntity) {

        Class existing = getClassById(id);

        existing.setStandard(classEntity.getStandard());
        existing.setDivision(classEntity.getDivision());
        existing.setTotalStudents(classEntity.getTotalStudents());
        existing.setBoys(classEntity.getBoys());
        existing.setGirls(classEntity.getGirls());

        // 🔥 IMPORTANT: regenerate className on update
        String className = existing.getStandard() + "-" + existing.getDivision();
        existing.setClassName(className);

        return classRepository.save(existing);
    }

    // ✅ DELETE
    @Override
    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }
}