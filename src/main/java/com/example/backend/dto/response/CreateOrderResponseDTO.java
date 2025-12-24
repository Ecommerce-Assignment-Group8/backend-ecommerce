package com.example.backend.dto.response;

import java.util.Date;

import com.example.backend.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponseDTO {
    private Integer id;
    private String paymentMethod;
    private Order.Status status;
    private Integer totalPrice;
    private String shippingAddress;
    private Date orderDate;
    private String message;
}
