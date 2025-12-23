package com.example.backend.dto.response;

import com.example.backend.entity.Order;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderResponseDTO {
    private Integer id;
    private String paymentMethod;
    private Order.Status status;
    private Integer totalPrice;
    private String shippingAddress;
    private Date orderDate;

}
