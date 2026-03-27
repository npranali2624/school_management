package com.example.school_management.service;

import com.example.school_management.entity.Parent;
import java.util.List;

public interface ParentService {

    Parent createParent(Parent parent);

    Parent getParentById(Long id);

    List<Parent> getAllParents();

    Parent updateParent(Long id, Parent parent);

    void deleteParent(Long id);
}