package com.example.studentapp.dto;

import jakarta.validation.constraints.*;

public record StudentSelfUpdateRequest(
        @Email @NotBlank String emailId,
        @NotBlank String phoneNo
) {}
