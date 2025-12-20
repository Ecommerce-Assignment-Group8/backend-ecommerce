package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue()
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
