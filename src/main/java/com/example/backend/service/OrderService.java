package com.example.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.dto.CreateOrderRequestDTO;
import com.example.backend.dto.response.CreateOrderResponseDTO;
import com.example.backend.dto.response.OrderItemResponseDTO;
import com.example.backend.dto.response.OrderListResponseDTO;
import com.example.backend.dto.response.OrderResponseDTO;
import com.example.backend.entity.CartItem;
import com.example.backend.entity.Order;
import com.example.backend.entity.OrderItem;
import com.example.backend.entity.Product;
import com.example.backend.entity.User;
import com.example.backend.repository.CartItemRepository;
import com.example.backend.repository.OrderItemRepository;
import com.example.backend.repository.OrderRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

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

    @Transactional
    public ResponseEntity<CreateOrderResponseDTO> createOrder(Integer userId, CreateOrderRequestDTO request) {
        try {
            Optional<User> userOpt = userRepository.findById(userId);
            if (!userOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CreateOrderResponseDTO(null, null, null, null, null, null, "User not found"));
            }

            List<CartItem> cartItems = cartItemRepository.findAllById(request.getCartItemIds());
            if (cartItems.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CreateOrderResponseDTO(null, null, null, null, null, null, "Cart is empty"));
            }

            Integer totalPrice = 0;
            for (CartItem cartItem : cartItems) {
                totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
            }

            Order order = new Order();
            order.setUser(userOpt.get());
            order.setPaymentMethod(request.getPaymentMethod());
            order.setShippingAddress(request.getShippingAddress());
            order.setTotalPrice(totalPrice);
            order.setStatus(Order.Status.PENDING);
            order.setOrderDate(new Date());

            order = orderRepository.save(order);

            for (CartItem cartItem : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItemRepository.save(orderItem);

                Product product = cartItem.getProduct();
                product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
                productRepository.save(product);
            }

            cartItemRepository.deleteAll(cartItems);

            CreateOrderResponseDTO response = new CreateOrderResponseDTO(
                    order.getId(),
                    order.getPaymentMethod(),
                    order.getStatus(),
                    order.getTotalPrice(),
                    order.getShippingAddress(),
                    order.getOrderDate(),
                    "Order created successfully"
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CreateOrderResponseDTO(null, null, null, null, null, null, 
                            "Error creating order: " + e.getMessage()));
        }
    }

    private OrderResponseDTO toOrderResponseDTO(Order order) {
        return new OrderResponseDTO(order.getId(),order.getPaymentMethod(),order.getStatus(),order.getTotalPrice(),order.getShippingAddress(),order.getOrderDate());
    }

    private OrderItemResponseDTO toOrderItemResponseDTO(OrderItem orderItem) {
        return new OrderItemResponseDTO(orderItem.getId(),orderItem.getQuantity(),orderItem.getProduct().getId(),orderItem.getProduct().getName(),orderItem.getProduct().getDescription(),orderItem.getProduct().getPrice(),orderItem.getProduct().getImage());
    }
}
