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
@Tag(name = "Payment API", description = "API thanh toán VietQR + SePay")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/vietqr")
    @Operation(summary = "Tạo thanh toán VietQR", 
               description = "Tạo mã QR thanh toán và trả về URL ảnh QR để hiển thị")
    public ResponseEntity<PaymentResponseDTO> createVietQRPayment(@RequestBody PaymentRequestDTO request) {
        PaymentResponseDTO response = paymentService.createVietQRPayment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify")
    @Operation(summary = "Xác nhận thanh toán", 
               description = "FE polling endpoint để kiểm tra trạng thái thanh toán từ SePay")
    public ResponseEntity<PaymentResponseDTO> verifyPayment(@RequestParam Integer orderId) {
        PaymentResponseDTO response = paymentService.verifyPayment(orderId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order/{orderId}")
    @Operation(summary = "Lịch sử thanh toán theo đơn hàng", 
               description = "Lấy danh sách các lần thanh toán của một đơn hàng")
    public ResponseEntity<List<Payment>> getPaymentsByOrder(@PathVariable Integer orderId) {
        List<Payment> payments = paymentService.getPaymentsByOrderId(orderId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/history/{userId}")
    @Operation(summary = "Lịch sử thanh toán theo user", 
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

    @GetMapping("/debug/sepay")
    @Operation(summary = "Debug SePay", 
               description = "Kiểm tra kết nối SePay API và xem transactions")
    public ResponseEntity<java.util.Map<String, Object>> debugSePay() {
        return ResponseEntity.ok(paymentService.debugSePayTransactions());
    }
}
