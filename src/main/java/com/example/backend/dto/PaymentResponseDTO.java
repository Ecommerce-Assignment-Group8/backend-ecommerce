package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDTO {
    private Long paymentId;
    private Integer orderId;
    private Long amount;
    private String status;
    private String message;
    
    // VietQR fields
    private String paymentRef;
    private String qrImageUrl;
    private String bankBin;
    private String bankAccountNumber;
    private String bankAccountName;
    
    // Verification fields
    private String sepayTransactionRef;
    private String verifiedAt;
}
