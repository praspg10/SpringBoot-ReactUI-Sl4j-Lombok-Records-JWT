package com.example.studentapp;

public class AdimEncryptPwd {
    
    
public static void main(String[] args) {
    System.out.println(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("admin@123"));
}

}