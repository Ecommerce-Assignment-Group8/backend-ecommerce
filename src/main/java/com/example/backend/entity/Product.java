package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    
    public enum Category {
    // Key(displayName)
    PROTEIN("Protein"),
    PRE_WORKOUT("Pre-Workout"),
    AMINO_ACIDS("Amino Acids"),
    CREATINE("Creatine"),
    WEIGHT_MANAGEMENT("Giảm cân & Đốt mỡ"),
    MASS_GAINER("Tăng cân"),
    VITAMINS_MINERALS("Vitamin & Khoáng chất"),
    RECOVERY_SLEEP("Phục hồi & Giấc ngủ"),
    ENERGY_ENDURANCE("Năng lượng & Sức bền");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
    private Integer price;

    private String image;

    private Integer stockQuantity;
    
    @Enumerated(EnumType.STRING)
    private Category category;
}
