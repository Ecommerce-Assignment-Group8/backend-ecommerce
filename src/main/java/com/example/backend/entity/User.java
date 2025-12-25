package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    public enum Gender { MALE, FEMALE }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String role;
    private String phoneNumber;
    private String email;
    @JsonIgnore
    private String password;
    private String dateOfBirth;
    @Column(columnDefinition = "text")
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
    @Column(columnDefinition = "text")
    private String specialty;
    @Column(nullable = true)
    private Integer experienceYear;
    private boolean isBusinesses;
    private String address;
    private String taxCode;
    private String businessName;

    
}
