package com.example.studentapp.service.impl;

import com.example.studentapp.dto.*;
import com.example.studentapp.entity.Student;
import com.example.studentapp.repository.StudentRepository;
import com.example.studentapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
// import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    // private final PasswordEncoder passwordEncoder;

    private StudentDto toDto(Student s) {
        return new StudentDto(
                s.getId(),
                s.getName(),
                s.getEmailId(),
                s.getAddress(),
                s.getPhoneNo(),
                s.getCourse(),
                s.getLoginId(),
                s.getRole().name()
        );
    }

    @SuppressWarnings("null")
    @Override
    public StudentDto createStudent(CreateStudentRequest request) {
        log.info("Creating student with loginId={}", request.loginId());
        Student student = Student.builder()
                .name(request.name())
                .emailId(request.emailId())
                .address(request.address())
                .phoneNo(request.phoneNo())
                .course(request.course())
                .loginId(request.loginId())
                .loginPassword(request.loginPassword())
                .role(Student.Role.valueOf(request.role()))
                .build();
        return toDto(studentRepository.save(student));
    }

    @Override
    public StudentDto updateStudent(Long id, UpdateStudentRequest request) {
        log.info("Updating student id={}", id);
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        s.setName(request.name());
        s.setEmailId(request.emailId());
        s.setAddress(request.address());
        s.setPhoneNo(request.phoneNo());
        s.setCourse(request.course());
        return toDto(studentRepository.save(s));
    }

    @Override
    public StudentDto selfUpdateStudent(Long id, StudentSelfUpdateRequest request, String currentLoginId) {
        log.info("Self updating student id={} by loginId={}", id, currentLoginId);
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        if (!s.getLoginId().equals(currentLoginId)) {
            throw new RuntimeException("Not allowed");
        }
        s.setEmailId(request.emailId());
        s.setPhoneNo(request.phoneNo());
        return toDto(studentRepository.save(s));
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("Deleting student id={}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public void deleteStudents(List<Long> ids) {
        log.info("Batch deleting students ids={}", ids);
        ids.forEach(studentRepository::deleteById);
    }

    @Override
    public StudentDto getStudent(Long id) {
        return studentRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public Page<StudentDto> getStudents(Pageable pageable, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageReq = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return studentRepository.findAll(pageReq).map(this::toDto);
    }

    @Override
    public StudentDto getByLoginId(String loginId) {
        return studentRepository.findByLoginId(loginId)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}
