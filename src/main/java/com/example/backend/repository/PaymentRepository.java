package com.example.backend.repository;

import com.example.backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    List<Payment> findByUserId(Integer userId);
    
    List<Payment> findByOrderId(Integer orderId);
    
    Optional<Payment> findByPaymentRef(String paymentRef);
    
    Optional<Payment> findBySepayTransactionRef(String sepayTransactionRef);
    
    Optional<Payment> findTopByOrderIdOrderByCreatedAtDesc(Integer orderId);
}
