package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    public enum Status { FINISHED, PENDING, CANCELLED }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer totalAmount;
    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private User trainee;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private User trainer;
}
