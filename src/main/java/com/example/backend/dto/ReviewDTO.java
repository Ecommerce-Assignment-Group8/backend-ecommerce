package com.example.backend.dto;
import java.util.Date;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ReviewDTO {
    
    private String comment;
    private Integer rating;
    @Column(nullable = false)
    private Integer traineeId;
    @Column(nullable = false)
    private Integer trainerId;
}
