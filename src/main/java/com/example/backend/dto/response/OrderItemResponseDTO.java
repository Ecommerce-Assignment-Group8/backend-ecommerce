package com.example.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDTO {
    private Integer id;
    private Integer quantity;
    private Integer productId;
    private String productName;
    private String productDescription;
    private Integer productPrice;
    private String productImage;
}
