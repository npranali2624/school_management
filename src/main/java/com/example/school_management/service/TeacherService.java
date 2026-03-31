package com.example.school_management.service;

import com.example.school_management.entity.Teacher;
import java.util.List;

public interface TeacherService {
    Teacher createTeacher(Teacher teacher);
    Teacher getTeacherById(Long id);
    List<Teacher> getAllTeachers();
    Teacher updateTeacher(Long id, Teacher teacher);
    void deleteTeacher(Long id);
}