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
    @GeneratedValue()
    private Integer id;
    private Date date;
    private Status status;
    private int totalAmount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Booking booking;


}
