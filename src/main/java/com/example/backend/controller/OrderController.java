package com.example.backend.controller;

import com.example.backend.dto.response.OrderListResponseDTO;
import com.example.backend.repository.OrderItemRepository;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{user_id}")
    public ResponseEntity<List<OrderListResponseDTO>> findByUserId(@PathVariable Integer user_id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof Long) {
            Long authenticatedId = (Long) principal;
            System.out.println("Authenticated User ID: " + authenticatedId);
        } else {
            System.out.println("User is Anonymous");
        }
        String role =  (String) SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);
        System.out.println("Role: " + role);
        return orderService.findOrderByUserId(user_id);
    }
}
