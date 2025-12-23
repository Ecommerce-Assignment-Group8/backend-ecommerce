package com.example.backend.factory.userfactory;

import com.example.backend.dto.RegisterRequest;
import com.example.backend.dto.Role;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserFactoryProvider {
    private final Map<Role, UserFactory<?>> userFactoryMap;
    public UserFactoryProvider(List<UserFactory<?>> userFactoryList) {
        this.userFactoryMap = userFactoryList.stream()
                .collect(Collectors.toMap(UserFactory::getRole, f->f));
    }

    @SuppressWarnings("unchecked")
    public <T extends RegisterRequest> UserFactory<T> getFactory(Role role) {
        return (UserFactory<T>) userFactoryMap.get(role);
    }
}
