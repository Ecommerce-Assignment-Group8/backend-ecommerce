package com.example.backend.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class TrainerRegisterRequest implements RegisterRequest {
    @Email
    private String email;

    private String password;
    private String fullName;
    private String certificate;
    private String specialty;
    private Integer experienceYear;
    private String bio;

    @Override
    public Role getRole() {
        return Role.TRAINER;
    }

}
