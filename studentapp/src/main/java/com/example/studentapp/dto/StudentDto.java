package com.example.studentapp.dto;

public record StudentDto(
        Long id,
        String name,
        String emailId,
        String address,
        String phoneNo,
        String course,
        String loginId,
        String role
) {}
