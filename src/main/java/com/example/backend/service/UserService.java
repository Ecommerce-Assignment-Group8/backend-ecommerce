package com.example.backend.service;


import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.RegisterRequest;
import com.example.backend.dto.response.RegisterResponse;
import com.example.backend.entity.User;
import com.example.backend.entity.User.Gender;
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
import java.util.List;

import com.example.backend.dto.UserCreateDTO;
import com.example.backend.dto.UserUpdateDTO;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final UserFactoryProvider factoryProvider;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UserService(UserFactoryProvider factoryProvider,
                       PasswordEncoder passwordEncoder, JwtService jwtService) {
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
    public User createUser(UserCreateDTO user) {
        // hash password or other business logic can be added here
        User newUser = new User();
        newUser.setFullName(user.getFullName());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setPassword(user.getPassword());

        newUser.setTrainee(user.isTrainee());
        newUser.setTrainer(user.isTrainer());
        newUser.setBusinesses(user.isBusinesses());

        if(user.isTrainer()) {
            newUser.setRole("TRAINER");
        }
        else if(user.isBusinesses()) {
            newUser.setRole("BUSINESSES");
        }
        else {
            newUser.setRole("TRAINEE");
        }

        newUser.setAddress(user.getAddress());
        newUser.setGoal(user.getGoal());
        newUser.setHeight(user.getHeight());
        newUser.setWeight(user.getWeight());
        newUser.setBio(user.getBio());
        newUser.setCertificate(user.getCertificate());
        newUser.setSpecialty(user.getSpecialty());
        newUser.setExperienceYear(user.getExperienceYear());
        newUser.setDateOfBirth(user.getDateOfBirth());
        newUser.setEmail(user.getEmail());
        newUser.setGender(user.getGender());
        newUser.setTaxCode(user.getTaxCode());
        newUser.setBusinessName(user.getBusinessName());

        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllTrainers(String fullName, Gender gender, String specialty, Integer minExp) {
        return userRepository.searchTrainers(fullName, gender, specialty, minExp);
    }

    public List<User> getAllTrainees() {
        return userRepository.findByRole("TRAINEE");
    }

    public List<User> getAllBusinesses() {
        return userRepository.findByRole("BUSINESS");
    }


    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Integer id, UserUpdateDTO updateDTO) {
        // check if user exists
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // update fields
        user.setFullName(updateDTO.getFullName());
        user.setPhoneNumber(updateDTO.getPhoneNumber());
        user.setAddress(updateDTO.getAddress());
        user.setGoal(updateDTO.getGoal());
        user.setHeight(updateDTO.getHeight());
        user.setWeight(updateDTO.getWeight());
        user.setBio(updateDTO.getBio());
        user.setCertificate(updateDTO.getCertificate());
        user.setSpecialty(updateDTO.getSpecialty());
        user.setExperienceYear(updateDTO.getExperienceYear());
        user.setDateOfBirth(updateDTO.getDateOfBirth());
        user.setEmail(updateDTO.getEmail());
        user.setGender(updateDTO.getGender());
        user.setTaxCode(updateDTO.getTaxCode());
        user.setBusinessName(updateDTO.getBusinessName());
        // update other fields as necessary

        return userRepository.save(user);
    }

    // delete user by id
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }
}