package com.example.backend.controller;

import com.example.backend.dto.PaymentRequestDTO;
import com.example.backend.dto.PaymentResponseDTO;
import com.example.backend.entity.Payment;
import com.example.backend.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@Tag(name = "Payment API", description = "API cổng thanh toán Stripe")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-checkout-session")
    @Operation(summary = "Tạo phiên thanh toán", 
               description = "Tạo phiên thanh toán Stripe Checkout và trả về URL chuyển hướng đến trang thanh toán")
    public ResponseEntity<PaymentResponseDTO> createCheckoutSession(@RequestBody PaymentRequestDTO request) {
        PaymentResponseDTO response = paymentService.createCheckoutSession(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/success")
    @Operation(summary = "Xử lý thanh toán thành công", 
               description = "Callback endpoint khi thanh toán thành công từ Stripe")
    public ResponseEntity<PaymentResponseDTO> handlePaymentSuccess(@RequestParam String session_id) {
        PaymentResponseDTO response = paymentService.handlePaymentSuccess(session_id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cancel")
    @Operation(summary = "Xử lý hủy thanh toán", 
               description = "Callback endpoint khi người dùng hủy thanh toán")
    public ResponseEntity<PaymentResponseDTO> handlePaymentCancel() {
        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setStatus("CANCELLED");
        response.setMessage("Thanh toán đã bị hủy");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{userId}")
    @Operation(summary = "Lịch sử thanh toán", 
               description = "Lấy lịch sử thanh toán của người dùng")
    public ResponseEntity<List<Payment>> getPaymentHistory(@PathVariable Integer userId) {
        List<Payment> payments = paymentService.getPaymentHistory(userId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{paymentId}")
    @Operation(summary = "Chi tiết thanh toán", 
               description = "Lấy thông tin chi tiết của một giao dịch thanh toán")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }
}
