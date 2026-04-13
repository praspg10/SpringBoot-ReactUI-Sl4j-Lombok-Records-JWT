package com.example.studentapp;

public class EncryptPwdVerify {
  
    
    public static void main(String[] args) {
    var enc = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    System.out.println(enc.matches("admin@123", "$2a$10$RPxpfJjQvJDg7yjP9e4SQOmBkrKLDXKVrMAUWxx8V34sWWIYFHeEK"));
}

}
