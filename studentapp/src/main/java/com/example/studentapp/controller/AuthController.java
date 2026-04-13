package com.example.studentapp.controller;

import com.example.studentapp.dto.*;
import com.example.studentapp.entity.Student;
import com.example.studentapp.repository.StudentRepository;
import com.example.studentapp.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    //  private final AuthenticationManager authenticationManager;
    private final StudentRepository studentRepository;
    private final JwtService jwtService;
    // private final PasswordEncoder passwordEncoder;

    // @PostMapping("/login")
    // public AuthResponse login(@RequestBody AuthRequest request) {
    //     log.info("Login attempt for {}", request.loginId());
    //     log.info("Login attempt for pwd {}", request.loginPassword());
    //     Authentication auth = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(request.loginId(), request.loginPassword())
    //     );
    //     Student student = studentRepository.findByLoginId(request.loginId())
    //             .orElseThrow(() -> new RuntimeException("User not found"));
    //     String token = jwtService.generateToken(student);
    //     return new AuthResponse(token, student.getRole().name(), student.getId());
    // }

    @PostMapping("/login")
public AuthResponse login(@RequestBody AuthRequest request) {
log.info(">>> LOGIN CONTROLLER REACHED <<<");

    // Log the attempt
    log.info("Login attempt for {}", request.loginId());

    // Skip authentication completely
    Student student = studentRepository.findByLoginId(request.loginId())
            .orElseThrow(() -> new RuntimeException("User not found"));

    // Ignore password check
    // No authenticationManager.authenticate(...)

    // Generate JWT token normally
    String token = jwtService.generateToken(student);

    return new AuthResponse(token, student.getRole().name(), student.getId());
}

}
