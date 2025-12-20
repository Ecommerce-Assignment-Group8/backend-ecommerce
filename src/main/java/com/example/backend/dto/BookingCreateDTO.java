package com.example.backend.dto;

import java.util.Date;

import com.example.backend.entity.Booking.Status;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class BookingCreateDTO {
    @Column(nullable = false)
    private Integer traineeId;

    @Column(nullable = false)
    private Integer trainerId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private Integer totalAmount;
}
