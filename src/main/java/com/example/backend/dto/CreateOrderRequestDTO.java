package com.example.backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequestDTO {
    private String paymentMethod;
    private String shippingAddress;
    private List<Integer> cartItemIds;

    // Frontend fields - for direct order creation without cart
    private Integer userId;
    private Integer totalAmount;
    private List<OrderItemData> items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemData {
        private Integer productId;
        private Integer quantity;
        private Integer price;
    }
}
