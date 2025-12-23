package com.example.backend.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class BusinessRegisterRequest implements RegisterRequest {
    @Email
    private String email;

    private String password;
    private String businessName;
    private String taxCode;
    private String address;

    @Override
    public Role getRole() {
        return Role.BUSINESS;
    }
}
