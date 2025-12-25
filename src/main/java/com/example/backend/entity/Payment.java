package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "payments",
        indexes = {
                @Index(name = "idx_payment_order", columnList = "order_id"),
                @Index(name = "idx_payment_ref", columnList = "paymentRef", unique = true),
                @Index(name = "idx_payment_sepay_txn", columnList = "sepayTransactionRef")
        }
)
public class Payment {

    public enum PaymentStatus { PENDING, COMPLETED, FAILED, CANCELLED }
    public enum PaymentMethod { STRIPE, VIETQR_SEPAY }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    // ===== VietQR fields =====
    @Column(nullable = false, unique = true, length = 64)
    private String paymentRef;

    @Column(columnDefinition = "text")
    private String qrImageUrl;

    private String bankBin;
    private String bankAccountNumber;
    private String bankAccountName;

    // ===== SePay verification fields =====
    @Column(unique = true)
    private String sepayTransactionRef;

    private LocalDateTime verifiedAt;

    // ===== timestamps =====
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (currency == null) currency = "VND";
        if (status == null) status = PaymentStatus.PENDING;
        if (paymentMethod == null) paymentMethod = PaymentMethod.VIETQR_SEPAY;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
