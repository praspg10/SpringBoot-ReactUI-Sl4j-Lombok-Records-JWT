package com.example.studentapp.service;

import com.example.studentapp.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(CreateStudentRequest request);

    StudentDto updateStudent(Long id, UpdateStudentRequest request);

    StudentDto selfUpdateStudent(Long id, StudentSelfUpdateRequest request, String currentLoginId);

    void deleteStudent(Long id);

    void deleteStudents(List<Long> ids);

    StudentDto getStudent(Long id);

    Page<StudentDto> getStudents(Pageable pageable, String sortBy, String direction);

    StudentDto getByLoginId(String loginId);
}
