package com.example.school_management.exception;

/**
 * Thrown when trying to create a resource that already exists.
 *
 * Example: Student with same Aadhar number already exists → throw this
 *
 * Usage:
 *   throw new DuplicateResourceException("Student with Aadhar 123456789012 already exists");
 *   throw new DuplicateResourceException("Student", "aadharNo", aadharNo);
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }

    /**
     * Convenience constructor — auto builds message like:
     * "Student already exists with aadharNo : '123456789012'"
     */
    public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exists with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
