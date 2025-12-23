package com.example.backend.service;

import com.example.backend.dto.response.OrderItemResponseDTO;
import com.example.backend.dto.response.OrderListResponseDTO;
import com.example.backend.dto.response.OrderResponseDTO;
import com.example.backend.entity.Order;
import com.example.backend.entity.OrderItem;
import com.example.backend.repository.OrderItemRepository;
import com.example.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public ResponseEntity<List<OrderListResponseDTO>> findOrderByUserId(Integer userId) {
        List<Order> orders = orderRepository.findOrdersByUserId(userId);
        List<OrderListResponseDTO> orderListResponse = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemRepository.findOrderItemsByOrderId(order.getId());
            OrderListResponseDTO orderListResponseDTO = new OrderListResponseDTO();
            List<OrderItemResponseDTO> orderItemResponseDTO = new ArrayList<>();
            orderListResponseDTO.setOrder(toOrderResponseDTO(order));
            for (OrderItem orderItem : orderItems) {
                orderItemResponseDTO.add(toOrderItemResponseDTO(orderItem));
            }
            orderListResponseDTO.setOrderItems(orderItemResponseDTO);

            orderListResponse.add(orderListResponseDTO);
        }
        return ResponseEntity.ok(orderListResponse);
    }
    private OrderResponseDTO toOrderResponseDTO(Order order) {
        return new OrderResponseDTO(order.getId(),order.getPaymentMethod(),order.getStatus(),order.getTotalPrice(),order.getShippingAddress(),order.getOrderDate());

    }
    private OrderItemResponseDTO toOrderItemResponseDTO(OrderItem orderItem) {
        return  new OrderItemResponseDTO(orderItem.getId(),orderItem.getQuantity(),orderItem.getProduct().getId(),orderItem.getProduct().getName(),orderItem.getProduct().getDescription(),orderItem.getProduct().getPrice(),orderItem.getProduct().getImage());
    }
}
