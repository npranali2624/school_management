package com.example.school_management.service;

import com.example.school_management.entity.Class;

import java.util.List;

public interface ClassService {

    Class createClass(Class classEntity);

    List<Class> getAllClasses();

    Class getClassById(Long id);

    Class updateClass(Long id, Class classEntity);

    void deleteClass(Long id);
}