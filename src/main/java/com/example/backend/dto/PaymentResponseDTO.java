package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    private Long paymentId;
    private String sessionId;
    private String checkoutUrl;
    private String status;
    private String message;
}
