package com.example.backend.dto;

import com.example.backend.entity.User.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserCreateDTO {
    private String role;
    private String phoneNumber;
    private String email;
    private String password;
    private String dateOfBirth;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private boolean isTrainee;
    private String goal;
    private String height;
    private String weight;
    private boolean isTrainer;
    private String certificate;
    private String bio;
    private String specialty;
    @Column(nullable = true)
    private Integer experienceYear;
    private boolean isBusinesses;
    private String address;
    private String taxCode;
    private String businessName;
}