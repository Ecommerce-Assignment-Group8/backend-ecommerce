package com.example.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponseDTO {
    private Integer id;
    private Integer productId;
    private String productName;
    private String productDescription;
    private Integer productPrice;
    private String productImage;
    private Integer quantity;
    private Integer totalPrice;
}
