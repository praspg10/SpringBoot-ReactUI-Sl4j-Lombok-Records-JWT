package com.example.studentapp.dto;

import jakarta.validation.constraints.*;

public record UpdateStudentRequest(
        @NotBlank String name,
        @Email @NotBlank String emailId,
        String address,
        @NotBlank String phoneNo,
        @NotBlank String course
) {}
