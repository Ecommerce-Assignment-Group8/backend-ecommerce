package com.example.backend.dto.response;

import com.example.backend.entity.Order;
import com.example.backend.entity.OrderItem;
import com.example.backend.repository.OrderItemRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListResponseDTO {
    OrderResponseDTO order;
    List<OrderItemResponseDTO> orderItems;
}
