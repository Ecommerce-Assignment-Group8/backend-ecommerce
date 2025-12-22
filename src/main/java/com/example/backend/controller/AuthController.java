package com.example.backend.controller;

import com.example.backend.dto.BusinessRegisterRequest;
import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.TraineeRegisterRequest;
import com.example.backend.dto.TrainerRegisterRequest;
import com.example.backend.dto.response.RegisterResponse;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register/trainee")
    public RegisterResponse registerTrainee(@RequestBody TraineeRegisterRequest req) {
        return userService.register(req);
    }

    @PostMapping("/register/trainer")
    public RegisterResponse registerTrainer(@RequestBody TrainerRegisterRequest req) {
        return userService.register(req);
    }

    @PostMapping("/register/business")
    public RegisterResponse registerBusiness(@RequestBody BusinessRegisterRequest req) {
        return userService.register(req);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return userService.login(req);
    }
}
