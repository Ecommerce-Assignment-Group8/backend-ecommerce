package com.example.backend.service;

import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.RegisterRequest;
import com.example.backend.dto.response.RegisterResponse;
import com.example.backend.entity.User;
import com.example.backend.exception.user.EmailAlreadyExistsException;
import com.example.backend.factory.userfactory.UserFactory;
import com.example.backend.factory.userfactory.UserFactoryProvider;
import com.example.backend.jwt.JwtService;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserFactoryProvider factoryProvider;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UserService(UserRepository userRepository,
                           UserFactoryProvider factoryProvider,
                       PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.factoryProvider = factoryProvider;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

    }

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        UserFactory factory = factoryProvider.getFactory(request.getRole());

        if (factory == null) {
            throw new IllegalArgumentException("Unsupported role");
        }

        User user = factory.create(request);
        User saveUser = userRepository.save(user);
        return new RegisterResponse(saveUser.getEmail());
    }
    public ResponseEntity<?>  login(LoginRequest  loginRequest) {
        User user = userRepository.findByEmailAndRole(loginRequest.getUsername(),loginRequest.getRole());
        if (user == null) {
            return ResponseEntity.badRequest().body(Map.of("message","invalid email or password"));
        }
        if(passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())) {
            String token = jwtService.generateToken(user.getEmail(),user.getRole());
            return ResponseEntity.ok(Map.of("token",token));
        }
        return ResponseEntity.badRequest().body(Map.of("message","invalid email or password"));
    }
}
