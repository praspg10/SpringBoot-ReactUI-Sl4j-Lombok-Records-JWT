package com.example.studentapp.dto;

import jakarta.validation.constraints.*;

public record CreateStudentRequest(
        @NotBlank String name,
        @Email @NotBlank String emailId,
        String address,
        @NotBlank String phoneNo,
        @NotBlank String course,
        @NotBlank String loginId,
        @NotBlank String loginPassword,
        @NotBlank String role // ADMIN or STUDENT
) {}
