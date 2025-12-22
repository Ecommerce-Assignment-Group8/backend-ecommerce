package com.example.backend.factory.userfactory;

import com.example.backend.dto.BusinessRegisterRequest;
import com.example.backend.dto.Role;
import com.example.backend.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BusinessFactory implements UserFactory<BusinessRegisterRequest> {
    private PasswordEncoder passwordEncoder;
    public BusinessFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Role getRole() {
        return Role.BUSINESS;
    }

    @Override
    public User create(BusinessRegisterRequest req) {
        User user = new User();
        user.setRole(Role.BUSINESS.toString());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        user.setBusinessName(req.getBusinessName());
        user.setTaxCode(req.getTaxCode());
        user.setAddress(req.getAddress());
        user.setBusinesses(true);

        return user;
    }
}
