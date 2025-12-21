package com.example.backend.service;


import com.example.backend.entity.User;
import com.example.backend.entity.User.Gender;
import com.example.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.backend.dto.UserCreateDTO;
import com.example.backend.dto.UserUpdateDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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