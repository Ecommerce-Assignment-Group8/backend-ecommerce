package com.example.backend.factory.userfactory;

import com.example.backend.dto.RegisterRequest;
import com.example.backend.dto.Role;
import com.example.backend.entity.User;

public interface UserFactory<T extends RegisterRequest>{
    Role getRole();
    User create(T request);
}
