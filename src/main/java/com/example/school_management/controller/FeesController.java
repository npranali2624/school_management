package com.example.school_management.controller;

import com.example.school_management.dto.FeesRequestDto;
import com.example.school_management.dto.FeesResponseDto;
import com.example.school_management.enums.Standard;
import com.example.school_management.service.FeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fees")
@RequiredArgsConstructor
public class FeesController {

    private final FeeService feesService;
    @PostMapping
    public ResponseEntity<FeesResponseDto> createFees(@Valid @RequestBody FeesRequestDto requestDTO) {
        FeesResponseDto response = feesService.createFees(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<FeesResponseDto>> getAllFees() {
        return ResponseEntity.ok(feesService.getAllFees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeesResponseDto> getFeesById(@PathVariable Long id) {
        return ResponseEntity.ok(feesService.getFeesById(id));
    }
    @GetMapping("/std/{std}")
    public ResponseEntity<List<FeesResponseDto>> getFeesByStd(@PathVariable Standard std) {
        return ResponseEntity.ok(feesService.getFeesByStd(std));
    }
    @GetMapping("/std/{std}/year/{academicYear}")
    public ResponseEntity<List<FeesResponseDto>> getFeesByStdAndYear(
            @PathVariable Standard std,
            @PathVariable String academicYear) {
        return ResponseEntity.ok(feesService.getFeesByStdAndYear(std, academicYear));
    }
    @PatchMapping("/{id}/pay")
    public ResponseEntity<FeesResponseDto> markAsPaid(@PathVariable Long id) {
        return ResponseEntity.ok(feesService.markAsPaid(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFees(@PathVariable Long id) {
        feesService.deleteFees(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/standards")
    public ResponseEntity<List<Integer>> getStandards() {
        List<Integer> standards = Arrays.stream(Standard.values())
                .map(Standard::getValue)
                .collect(Collectors.toList());
        return ResponseEntity.ok(standards);
    }
}