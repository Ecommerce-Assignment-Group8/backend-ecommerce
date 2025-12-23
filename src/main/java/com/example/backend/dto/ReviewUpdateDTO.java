package com.example.backend.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ReviewUpdateDTO {
    
    private String comment;
    private Integer rating;
}
