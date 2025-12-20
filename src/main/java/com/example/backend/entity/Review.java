package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Integer id;
    private Date reviewDate;
    private String comment;
    private Integer rating;
    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private User user;

}
