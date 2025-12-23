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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date reviewDate;
    @Column(columnDefinition = "text")
    private String comment;
    private Integer rating;
    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private User trainer;

}
