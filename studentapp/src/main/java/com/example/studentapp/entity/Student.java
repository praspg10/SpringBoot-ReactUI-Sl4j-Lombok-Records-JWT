package com.example.studentapp.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // auto-generate

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, name = "email_id")
    private String emailId;

    private String address;

    @Column(nullable = false, name = "phone_no")
    private String phoneNo;

    @Column(nullable = false)
    private String course; // Java, HTML, Spring

    @Column(nullable = false, unique = true, name = "login_id")
    private String loginId;

    @Column(nullable = false, name = "login_password")
    private String loginPassword; // store encoded

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ADMIN, STUDENT
    }
}
