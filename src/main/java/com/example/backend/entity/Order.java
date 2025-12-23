package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    public enum Status { FINISHED, PENDING, CANCELLED }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String paymentMethod;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer totalPrice;
    private String shippingAddress;
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private User user;
}
