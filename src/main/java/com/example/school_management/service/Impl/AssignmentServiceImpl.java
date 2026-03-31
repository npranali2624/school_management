package com.example.school_management.service.Impl;

import com.example.school_management.dto.AssignmentRequestDto;
import com.example.school_management.dto.AssignmentResponseDto;
import com.example.school_management.entity.Assignment;
import com.example.school_management.entity.Class;
import com.example.school_management.entity.Subject;
import com.example.school_management.entity.Teacher;
import com.example.school_management.enums.AssignmentMarkingType;
import com.example.school_management.enums.AssignmentStatus;
import com.example.school_management.enums.AssignmentType;
import com.example.school_management.exception.ResourceNotFoundException;
import com.example.school_management.repo.AssignmentRepository;
import com.example.school_management.repo.ClassRepository;
import com.example.school_management.repo.SubjectRepository;
import com.example.school_management.repo.TeacherRepo;
import com.example.school_management.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final TeacherRepo teacherRepository;
    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;


    @Override
    public AssignmentResponseDto createAssignment(AssignmentRequestDto dto) {

        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Teacher not found with id: " + dto.getTeacherId()));

        Class assignedClass = classRepository.findById(dto.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Class not found with id: " + dto.getClassId()));

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Subject not found with id: " + dto.getSubjectId()));

        validateMarkingType(dto.getAssignmentType(), dto.getMarkingType());
        validateMarksFields(dto.getMarkingType(), dto.getTotalMarks(), dto.getPassingMarks());
        validateLateSubmission(dto.getAllowLateSubmission(), dto.getDueDate(), dto.getLateSubmissionDueDate());

        Assignment assignment = new Assignment();
        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setAssignmentType(dto.getAssignmentType());
        assignment.setMarkingType(dto.getMarkingType());
        assignment.setTotalMarks(dto.getTotalMarks());
        assignment.setPassingMarks(dto.getPassingMarks());
        assignment.setAssignedDate(LocalDateTime.now());
        assignment.setDueDate(dto.getDueDate());
        assignment.setStatus(AssignmentStatus.DRAFT);
        assignment.setAllowLateSubmission(dto.getAllowLateSubmission() != null ? dto.getAllowLateSubmission() : false);
        assignment.setLateSubmissionDueDate(Boolean.TRUE.equals(dto.getAllowLateSubmission()) ? dto.getLateSubmissionDueDate() : null);
        assignment.setAllowedFileTypes(dto.getAllowedFileTypes());
        assignment.setTeacher(teacher);
        assignment.setAssignedClass(assignedClass);
        assignment.setSubject(subject);

        Assignment saved = assignmentRepository.save(assignment);
        log.info("Assignment created with id: {}", saved.getId());
        return mapToResponseDto(saved);
    }



    @Override
    public AssignmentResponseDto updateAssignment(Long id, AssignmentRequestDto dto) {

        Assignment assignment = findAssignmentById(id);

        switch (assignment.getStatus()) {
            case DRAFT:
            case PUBLISHED:
                break;
            case CLOSED:
                throw new IllegalStateException("CLOSED assignment cannot be updated.");
            case CANCELLED:
                throw new IllegalStateException("CANCELLED assignment cannot be updated.");
            default:
                throw new IllegalStateException("Invalid status.");
        }

        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Teacher not found with id: " + dto.getTeacherId()));

        Class assignedClass = classRepository.findById(dto.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Class not found with id: " + dto.getClassId()));

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Subject not found with id: " + dto.getSubjectId()));

        validateMarkingType(dto.getAssignmentType(), dto.getMarkingType());
        validateMarksFields(dto.getMarkingType(), dto.getTotalMarks(), dto.getPassingMarks());
        validateLateSubmission(dto.getAllowLateSubmission(), dto.getDueDate(), dto.getLateSubmissionDueDate());

        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setAssignmentType(dto.getAssignmentType());
        assignment.setMarkingType(dto.getMarkingType());
        assignment.setTotalMarks(dto.getTotalMarks());
        assignment.setPassingMarks(dto.getPassingMarks());
        assignment.setDueDate(dto.getDueDate());
        assignment.setAllowLateSubmission(dto.getAllowLateSubmission() != null ? dto.getAllowLateSubmission() : false);
        assignment.setLateSubmissionDueDate(Boolean.TRUE.equals(dto.getAllowLateSubmission()) ? dto.getLateSubmissionDueDate() : null);
        assignment.setAllowedFileTypes(dto.getAllowedFileTypes());
        assignment.setTeacher(teacher);
        assignment.setAssignedClass(assignedClass);
        assignment.setSubject(subject);

        Assignment updated = assignmentRepository.save(assignment);
        log.info("Assignment updated with id: {}", updated.getId());
        return mapToResponseDto(updated);
    }



    @Override
    @Transactional
    public AssignmentResponseDto getAssignmentById(Long id) {
        Assignment assignment = findAssignmentById(id);
        autoCloseIfOverdue(assignment);
        return mapToResponseDto(assignment);
    }

    @Override
    public List<AssignmentResponseDto> getAllAssignments() {
        return assignmentRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }


    @Override
    public void deleteAssignment(Long id) {
        Assignment assignment = findAssignmentById(id);

        switch (assignment.getStatus()) {
            case DRAFT:
            case CANCELLED:

                break;
            case PUBLISHED:
                throw new IllegalStateException("PUBLISHED assignment cannot be deleted. Cancel it first.");
            case CLOSED:
                throw new IllegalStateException("CLOSED assignment cannot be deleted.");
            default:
                throw new IllegalStateException("Invalid status.");
        }

        assignmentRepository.delete(assignment);
        log.info("Assignment deleted with id: {}", id);
    }



    @Override
    public AssignmentResponseDto publishAssignment(Long id) {
        Assignment assignment = findAssignmentById(id);

        switch (assignment.getStatus()) {
            case DRAFT:
                if (assignment.getDueDate().isBefore(LocalDateTime.now())) {
                    throw new IllegalStateException(
                            "Cannot publish an assignment whose due date has already passed.");
                }
                assignment.setStatus(AssignmentStatus.PUBLISHED);
                break;
            case PUBLISHED:
                throw new IllegalStateException("Assignment is already PUBLISHED.");
            case CLOSED:
                throw new IllegalStateException("CLOSED assignment cannot be published.");
            case CANCELLED:
                throw new IllegalStateException("CANCELLED assignment cannot be published.");
            default:
                throw new IllegalStateException("Invalid status.");
        }

        Assignment saved = assignmentRepository.save(assignment);
        log.info("Assignment published with id: {}", id);
        return mapToResponseDto(saved);
    }



    @Override
    public AssignmentResponseDto cancelAssignment(Long id) {
        Assignment assignment = findAssignmentById(id);

        switch (assignment.getStatus()) {
            case DRAFT:
            case PUBLISHED:
                assignment.setStatus(AssignmentStatus.CANCELLED);
                break;
            case CLOSED:
                throw new IllegalStateException("CLOSED assignment cannot be cancelled.");
            case CANCELLED:
                throw new IllegalStateException("Assignment is already CANCELLED.");
            default:
                throw new IllegalStateException("Invalid status.");
        }

        Assignment saved = assignmentRepository.save(assignment);
        log.info("Assignment cancelled with id: {}", id);
        return mapToResponseDto(saved);
    }



    private Assignment findAssignmentById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Assignment not found with id: " + id));
    }

    private void autoCloseIfOverdue(Assignment assignment) {
        switch (assignment.getStatus()) {
            case PUBLISHED:
                LocalDateTime effectiveDueDate = Boolean.TRUE.equals(assignment.getAllowLateSubmission())
                        && assignment.getLateSubmissionDueDate() != null
                        ? assignment.getLateSubmissionDueDate()
                        : assignment.getDueDate();

                if (effectiveDueDate != null && LocalDateTime.now().isAfter(effectiveDueDate)) {
                    assignment.setStatus(AssignmentStatus.CLOSED);
                    assignmentRepository.save(assignment);
                    log.info("Assignment auto-closed with id: {}", assignment.getId());
                }
                break;
            case DRAFT:
            case CLOSED:
            case CANCELLED:

                break;
            default:
                throw new IllegalStateException("Invalid status.");
        }
    }


    private void validateMarkingType(AssignmentType assignmentType, AssignmentMarkingType markingType) {
        if (assignmentType == null || markingType == null) return;

        boolean isCheckBased = assignmentType == AssignmentType.HOMEWORK
                || assignmentType == AssignmentType.CLASSWORK;

        boolean isCheckMarkingType = markingType == AssignmentMarkingType.CHECKED
                || markingType == AssignmentMarkingType.UNCHECKED;

        if (isCheckBased && !isCheckMarkingType) {
            throw new IllegalArgumentException(
                    "HOMEWORK and CLASSWORK assignments can only use CHECKED or UNCHECKED marking type.");
        }

        if (!isCheckBased && isCheckMarkingType) {
            throw new IllegalArgumentException(
                    "PROJECT, QUIZ, and EXAM assignments must use MARKS, GRADE, or PASS_FAIL marking type.");
        }
    }

    private void validateMarksFields(AssignmentMarkingType markingType, Integer totalMarks, Integer passingMarks) {
        if (markingType == null) return;

        switch (markingType) {
            case MARKS, PASS_FAIL -> {
                if (totalMarks == null) {
                    throw new IllegalArgumentException(
                            "Total marks are required for " + markingType + " marking type.");
                }
                if (passingMarks == null) {
                    throw new IllegalArgumentException(
                            "Passing marks are required for " + markingType + " marking type.");
                }
                if (passingMarks > totalMarks) {
                    throw new IllegalArgumentException(
                            "Passing marks cannot be greater than total marks.");
                }
            }
            case CHECKED, UNCHECKED -> {
                if (totalMarks != null || passingMarks != null) {
                    throw new IllegalArgumentException(
                            "Marks fields must not be set for CHECKED or UNCHECKED marking type.");
                }
            }
            case GRADE -> {

            }
        }
    }


    private void validateLateSubmission(Boolean allowLateSubmission, LocalDateTime dueDate, LocalDateTime lateSubmissionDueDate) {
        if (Boolean.TRUE.equals(allowLateSubmission)) {
            if (lateSubmissionDueDate == null) {
                throw new IllegalArgumentException(
                        "Late submission due date is required when late submission is allowed.");
            }
            if (dueDate != null && !lateSubmissionDueDate.isAfter(dueDate)) {
                throw new IllegalArgumentException(
                        "Late submission due date must be after the original due date.");
            }
        } else {
            if (lateSubmissionDueDate != null) {
                throw new IllegalArgumentException(
                        "Late submission due date must not be set when late submission is not allowed.");
            }
        }
    }



    private AssignmentResponseDto mapToResponseDto(Assignment assignment) {

        AssignmentResponseDto dto = new AssignmentResponseDto();

        dto.setId(assignment.getId());
        dto.setTitle(assignment.getTitle());
        dto.setDescription(assignment.getDescription());
        dto.setAssignmentType(assignment.getAssignmentType());
        dto.setMarkingType(assignment.getMarkingType());
        dto.setTotalMarks(assignment.getTotalMarks());
        dto.setPassingMarks(assignment.getPassingMarks());
        dto.setAssignedDate(assignment.getAssignedDate());
        dto.setDueDate(assignment.getDueDate());
        dto.setStatus(assignment.getStatus());
        dto.setAllowLateSubmission(assignment.getAllowLateSubmission());
        dto.setLateSubmissionDueDate(assignment.getLateSubmissionDueDate());
        dto.setAllowedFileTypes(assignment.getAllowedFileTypes());

        if (assignment.getTeacher() != null) {
            dto.setTeacherId(assignment.getTeacher().getId());
            dto.setTeacherName(assignment.getTeacher().getFirstName() + " " + assignment.getTeacher().getLastName());
        }

        if (assignment.getAssignedClass() != null) {
            dto.setClassId(assignment.getAssignedClass().getId());
        }

        if (assignment.getSubject() != null) {
            dto.setSubjectId(assignment.getSubject().getId());
            dto.setSubjectName(assignment.getSubject().getSubjectName());
        }

        return dto;
    }
}