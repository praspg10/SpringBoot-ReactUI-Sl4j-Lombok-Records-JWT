package com.example.studentapp.dto;

public record AuthResponse(
        String token,
        String role,
        Long studentId
) {}
