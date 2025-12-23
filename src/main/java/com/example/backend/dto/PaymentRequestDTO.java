package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    private Integer userId;
    private Integer bookingId;
    private Long amount;
    private String currency;
    private String description;
    private String successUrl;
    private String cancelUrl;
}
