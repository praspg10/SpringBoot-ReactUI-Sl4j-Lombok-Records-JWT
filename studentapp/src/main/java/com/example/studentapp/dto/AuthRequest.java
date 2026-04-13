package com.example.studentapp.dto;

public record AuthRequest(
        String loginId,
        String loginPassword
) {}
