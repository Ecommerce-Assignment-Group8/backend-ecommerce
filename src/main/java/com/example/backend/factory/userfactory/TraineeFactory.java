package com.example.backend.factory.userfactory;

import com.example.backend.dto.Role;
import com.example.backend.dto.TraineeRegisterRequest;
import com.example.backend.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TraineeFactory implements UserFactory<TraineeRegisterRequest> {


    private final PasswordEncoder passwordEncoder;

    public TraineeFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Role getRole() {
        return Role.TRAINEE;
    }

    @Override
    public User create(TraineeRegisterRequest req) {
        User user = new User();
        user.setRole(Role.TRAINEE.toString());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setFullName(req.getFullName());
        user.setDateOfBirth(req.getDateOfBirth());
        user.setGender(req.getGender());

        user.setGoal(req.getGoal());
        user.setHeight(req.getHeight());
        user.setWeight(req.getWeight());
        user.setTrainee(true);

        return user;
    }
}
