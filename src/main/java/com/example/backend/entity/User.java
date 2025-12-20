package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    public enum Gender { MALE, FEMALE }
    @Id
    @GeneratedValue()
    private Integer id;

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
