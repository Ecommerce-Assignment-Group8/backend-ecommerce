package com.example.backend.repository;

import com.example.backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    List<Payment> findByUserId(Integer userId);
    
    Optional<Payment> findByStripeSessionId(String sessionId);
    
    Optional<Payment> findByStripePaymentIntentId(String paymentIntentId);
    
    List<Payment> findByBookingId(Integer bookingId);
}
