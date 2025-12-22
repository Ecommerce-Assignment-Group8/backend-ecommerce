package com.example.backend.dto;

import com.example.backend.entity.User.Gender;
import lombok.Data;

@Data
public class UserUpdateDTO {
    // Chỉ liệt kê các trường được phép cập nhật
    private String phoneNumber;
    private String email;
    private String dateOfBirth;
    private String fullName;
    private Gender gender;
    private String goal;
    private String height;
    private String weight;
    private String certificate;
    private String bio;
    private String specialty;
    private Integer experienceYear;
    private String address;
    private String taxCode;
    private String businessName;
}