package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String price;
    private String description;
    private String type;
    private String thumbNailUrl;
    private boolean isActive;
    private Integer duration;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private User trainer_id;
}
