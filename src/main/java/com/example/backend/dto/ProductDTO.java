package com.example.backend.dto;
import com.example.backend.entity.Product.Category;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private Integer price;
    private String image;
    private Integer stockQuantity;
    private Category category;
}
