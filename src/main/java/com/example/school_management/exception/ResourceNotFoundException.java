package com.example.school_management.exception;

/**
 * Thrown when a requested resource (Student, Teacher, etc.)
 * is not found in the database.
 *
 * Example: Student with ID 5 does not exist → throw this
 *
 * Usage:
 *   throw new ResourceNotFoundException("Student not found with ID: " + id);
 *   throw new ResourceNotFoundException("Student", "id", id);
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Convenience constructor — auto builds message like:
     * "Student not found with id : '5'"
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
