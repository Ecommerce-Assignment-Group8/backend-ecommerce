package com.example.backend.factory.userfactory;

import com.example.backend.dto.Role;
import com.example.backend.dto.TrainerRegisterRequest;
import com.example.backend.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TrainerFactory implements UserFactory<TrainerRegisterRequest> {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public TrainerFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Role getRole() {
        return Role.TRAINER;
    }

    @Override
    public User create(TrainerRegisterRequest req) {

        User user = new User();
        user.setRole(Role.TRAINER.toString());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setFullName(req.getFullName());

        user.setCertificate(req.getCertificate());
        user.setSpecialty(req.getSpecialty());
        user.setExperienceYear(req.getExperienceYear());
        user.setBio(req.getBio());
        user.setTrainer(true);

        return user;
    }
}
