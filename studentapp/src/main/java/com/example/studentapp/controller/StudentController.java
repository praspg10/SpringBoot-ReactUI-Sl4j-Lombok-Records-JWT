package com.example.studentapp.controller;

import com.example.studentapp.dto.*;
import com.example.studentapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;

    // ADMIN: create
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public StudentDto create(@Valid @RequestBody CreateStudentRequest request) {
        return studentService.createStudent(request);
    }

    // ADMIN: update
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public StudentDto update(@PathVariable Long id,
                             @Valid @RequestBody UpdateStudentRequest request) {
        return studentService.updateStudent(id, request);
    }

    // STUDENT: self update email & phone
    @PutMapping("/{id}/self")
    @PreAuthorize("hasRole('STUDENT')")
    public StudentDto selfUpdate(@PathVariable Long id,
                                 @Valid @RequestBody StudentSelfUpdateRequest request,
                                 Authentication auth) {
        return studentService.selfUpdateStudent(id, request, auth.getName());
    }

    // ADMIN: delete single
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    // ADMIN: batch delete
    @PostMapping("/batch-delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void batchDelete(@RequestBody List<Long> ids) {
        studentService.deleteStudents(ids);
    }

    // ADMIN: list with pagination & sorting
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<StudentDto> list(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy,
                                 @RequestParam(defaultValue = "ASC") String direction) {
        Pageable pageable = PageRequest.of(page, size);
        return studentService.getStudents(pageable, sortBy, direction);
    }

    // ADMIN: get by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public StudentDto get(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    // STUDENT: view own info
    @GetMapping("/me")
    @PreAuthorize("hasRole('STUDENT')")
    public StudentDto me(Authentication auth) {
        return studentService.getByLoginId(auth.getName());
    }
}
