package com.example.backend.dto;

import com.example.backend.entity.User;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class TraineeRegisterRequest implements RegisterRequest {
    @Email
    private String email;

    private String password;
    private String fullName;
    private String dateOfBirth;
    private User.Gender gender;

    private String goal;
    private String height;
    private String weight;

    @Override
    public Role getRole() {
        return Role.TRAINEE;
    }
}
