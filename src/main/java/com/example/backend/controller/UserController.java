package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.entity.User.Gender;
import com.example.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.backend.dto.UserCreateDTO;
import com.example.backend.dto.UserUpdateDTO;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody UserCreateDTO user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/trainers")
    public List<User> getAllTrainers(
    @RequestParam(required = false) String fullName,
        @RequestParam(required = false) Gender gender,
        @RequestParam(required = false) String specialty,
        @RequestParam(required = false) Integer minExp

    ) {
        return userService.getAllTrainers(fullName, gender, specialty, minExp);
    }

    @GetMapping("/businesses")
    public List<User> getAllBusinesses() {
        return userService.getAllBusinesses();
    }

    @GetMapping("/trainees")
    public List<User> getAllTrainees() {
        return userService.getAllTrainees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody UserUpdateDTO updateDTO) {
        User updatedUser = userService.updateUser(id, updateDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // API Xóa User
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully with id: " + id);
    }
}