package com.example.backend.service;

import com.example.backend.dto.PaymentRequestDTO;
import com.example.backend.dto.PaymentResponseDTO;
import com.example.backend.entity.Order;
import com.example.backend.entity.Payment;
import com.example.backend.entity.User;
import com.example.backend.repository.OrderRepository;
import com.example.backend.repository.PaymentRepository;
import com.example.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class PaymentService {

    @Value("${bank.bin:970423}")
    private String bankBin;

    @Value("${bank.account.number:0123456789}")
    private String bankAccountNumber;

    @Value("${bank.account.name:NGUYEN VAN A}")
    private String bankAccountName;

    @Value("${bank.template:compact}")
    private String bankTemplate;

    @Value("${sepay.api.url:https://my.sepay.vn/userapi}")
    private String sepayApiUrl;

    @Value("${sepay.api.token:}")
    private String sepayApiToken;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Tạo thanh toán VietQR cho đơn hàng
     */
    @Transactional
    public PaymentResponseDTO createVietQRPayment(PaymentRequestDTO request) {
        try {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

            Order order = orderRepository.findById(request.getOrderId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

            // Tạo paymentRef unique
            String paymentRef = generatePaymentRef(request.getOrderId());
            
            // Tạo QR URL
            String description = request.getDescription() != null 
                    ? request.getDescription() 
                    : "DH" + request.getOrderId() + " " + paymentRef;
            String qrImageUrl = generateVietQRUrl(request.getAmount(), description);

            // Tạo payment record
            Payment payment = new Payment();
            payment.setUser(user);
            payment.setOrder(order);
            payment.setAmount(request.getAmount());
            payment.setCurrency(request.getCurrency() != null ? request.getCurrency() : "VND");
            payment.setStatus(Payment.PaymentStatus.PENDING);
            payment.setPaymentMethod(Payment.PaymentMethod.VIETQR_SEPAY);
            payment.setPaymentRef(paymentRef);
            payment.setQrImageUrl(qrImageUrl);
            payment.setBankBin(bankBin);
            payment.setBankAccountNumber(bankAccountNumber);
            payment.setBankAccountName(bankAccountName);

            paymentRepository.save(payment);
            log.info("Created VietQR payment for order: {} with paymentRef: {}", request.getOrderId(), paymentRef);

            return PaymentResponseDTO.builder()
                    .paymentId(payment.getId())
                    .orderId(request.getOrderId())
                    .amount(request.getAmount())
                    .status("PENDING")
                    .message("Quét mã QR để thanh toán")
                    .paymentRef(paymentRef)
                    .qrImageUrl(qrImageUrl)
                    .bankBin(bankBin)
                    .bankAccountNumber(bankAccountNumber)
                    .bankAccountName(bankAccountName)
                    .build();

        } catch (Exception e) {
            log.error("Error creating VietQR payment: {}", e.getMessage());
            return PaymentResponseDTO.builder()
                    .status("ERROR")
                    .message("Lỗi khi tạo thanh toán: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Xác nhận thanh toán - FE polling endpoint
     */
    @Transactional
    public PaymentResponseDTO verifyPayment(Integer orderId) {
        Payment payment = paymentRepository.findTopByOrderIdOrderByCreatedAtDesc(orderId)
                .orElse(null);

        if (payment == null) {
            return PaymentResponseDTO.builder()
                    .orderId(orderId)
                    .status("NOT_FOUND")
                    .message("Không tìm thấy thanh toán cho đơn hàng này")
                    .build();
        }

        // Nếu đã COMPLETED thì trả về luôn
        if (payment.getStatus() == Payment.PaymentStatus.COMPLETED) {
            return PaymentResponseDTO.builder()
                    .paymentId(payment.getId())
                    .orderId(orderId)
                    .amount(payment.getAmount())
                    .status("COMPLETED")
                    .message("Thanh toán thành công!")
                    .paymentRef(payment.getPaymentRef())
                    .sepayTransactionRef(payment.getSepayTransactionRef())
                    .verifiedAt(payment.getVerifiedAt() != null ? payment.getVerifiedAt().toString() : null)
                    .build();
        }

        // Gọi SePay API để kiểm tra giao dịch
        SePayTransaction transaction = findTransactionFromSePay(payment.getAmount(), payment.getPaymentRef());

        if (transaction != null && transaction.isMatched()) {
            payment.setStatus(Payment.PaymentStatus.COMPLETED);
            payment.setSepayTransactionRef(transaction.getTransactionId());
            payment.setVerifiedAt(LocalDateTime.now());
            paymentRepository.save(payment);

            // Update order status to FINISHED
            Order order = payment.getOrder();
            order.setStatus(Order.Status.FINISHED);
            orderRepository.save(order);

            log.info("Payment verified for order: {} with sepayRef: {}", orderId, transaction.getTransactionId());

            return PaymentResponseDTO.builder()
                    .paymentId(payment.getId())
                    .orderId(orderId)
                    .amount(payment.getAmount())
                    .status("COMPLETED")
                    .message("Thanh toán thành công!")
                    .paymentRef(payment.getPaymentRef())
                    .sepayTransactionRef(transaction.getTransactionId())
                    .verifiedAt(payment.getVerifiedAt().toString())
                    .build();
        }

        return PaymentResponseDTO.builder()
                .paymentId(payment.getId())
                .orderId(orderId)
                .amount(payment.getAmount())
                .status("PENDING")
                .message("Đang chờ thanh toán...")
                .paymentRef(payment.getPaymentRef())
                .qrImageUrl(payment.getQrImageUrl())
                .build();
    }

    /**
     * Lấy lịch sử thanh toán của user
     */
    public List<Payment> getPaymentHistory(Integer userId) {
        return paymentRepository.findByUserId(userId);
    }

    /**
     * Lấy thông tin payment theo ID
     */
    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin thanh toán"));
    }

    /**
     * Lấy payment theo orderId
     */
    public List<Payment> getPaymentsByOrderId(Integer orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    /**
     * Debug: Lấy tất cả transactions từ SePay để kiểm tra
     */
    public Map<String, Object> debugSePayTransactions() {
        try {
            String url = sepayApiUrl + "/transactions/list";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + sepayApiToken);
            headers.set("Accept", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    Map.class);

            Map<String, Object> result = new java.util.HashMap<>();
            result.put("status", response.getStatusCode().toString());
            result.put("sepayApiUrl", sepayApiUrl);
            result.put("tokenConfigured", !sepayApiToken.isEmpty());
            result.put("response", response.getBody());
            return result;
        } catch (Exception e) {
            Map<String, Object> error = new java.util.HashMap<>();
            error.put("error", e.getMessage());
            error.put("sepayApiUrl", sepayApiUrl);
            error.put("tokenConfigured", !sepayApiToken.isEmpty());
            return error;
        }
    }

    // ===== Private helper methods =====

    private String generatePaymentRef(Integer orderId) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String uuid = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        return "PAY_OD" + orderId + "_" + timestamp + "_" + uuid;
    }

    private String generateVietQRUrl(Long amount, String addInfo) {
        try {
            String encodedAddInfo = URLEncoder.encode(addInfo, StandardCharsets.UTF_8.toString());
            return String.format(
                    "https://img.vietqr.io/image/%s-%s-%s.png?amount=%d&addInfo=%s&accountName=%s",
                    bankBin,
                    bankAccountNumber,
                    bankTemplate,
                    amount,
                    encodedAddInfo,
                    URLEncoder.encode(bankAccountName, StandardCharsets.UTF_8.toString())
            );
        } catch (UnsupportedEncodingException e) {
            log.error("Error encoding VietQR URL", e);
            throw new RuntimeException("Failed to generate VietQR URL", e);
        }
    }

    private SePayTransaction findTransactionFromSePay(Long amount, String paymentRef) {
        try {
            log.info("[SePay] Checking transactions for amount={}, paymentRef={}", amount, paymentRef);
            log.info("[SePay] API URL: {}, Token present: {}", sepayApiUrl, !sepayApiToken.isEmpty());
            
            String url = sepayApiUrl + "/transactions/list";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + sepayApiToken);
            headers.set("Accept", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    Map.class);

            log.info("[SePay] Response status: {}", response.getStatusCode());

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                log.info("[SePay] Response body keys: {}", response.getBody().keySet());


                List<Map<String, Object>> transactions = (List<Map<String, Object>>) response.getBody().get("transactions");

                for (Map<String, Object> t : transactions) {
                    log.info("[SePay] txn keys={}", t.keySet());
                    log.info("[SePay] raw txn={}", t);
                }

                if (transactions == null || transactions.isEmpty()) {
                    log.info("[SePay] No transactions found in response");
                    return null;
                }
                
                log.info("[SePay] Found {} transactions, checking for match...", transactions.size());
                
                for (Map<String, Object> t : transactions) {
                    Long txnAmount = toLong(t.get("amount_in"));
                    if (txnAmount == null) txnAmount = toLong(t.get("amount"));
                    String content = (String) t.get("transaction_content");
                    if (content == null) content = (String) t.get("description");
                    
                    log.info("[SePay] Transaction: amount={}, content={}", txnAmount, content);
                    
                    // Match by amount AND paymentRef in content
                    if (amount.equals(txnAmount)) {
                        log.info("[SePay] MATCH FOUND! txnId={}", t.get("id"));
                        return new SePayTransaction(
                                String.valueOf(t.get("id")),
                                txnAmount,
                                content,
                                true
                        );
                    }
                }
                
                log.info("[SePay] No matching transaction found for amount={} and paymentRef={}", amount, paymentRef);
            }
        } catch (Exception e) {
            log.error("[SePay] Error fetching transactions: {}", e.getMessage(), e);
        }
        return null;
    }

    private Long toLong(Object value) {
        if (value == null) return null;
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        if (value instanceof String) {
            try {
                // Handle "5000.00" format
                String str = (String) value;
                if (str.contains(".")) {
                    return (long) Double.parseDouble(str);
                }
                return Long.parseLong(str);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    // Inner class for SePay transaction
    private static class SePayTransaction {
        private final String transactionId;
        private final Long amount;
        private final String content;
        private final boolean matched;

        SePayTransaction(String transactionId, Long amount, String content, boolean matched) {
            this.transactionId = transactionId;
            this.amount = amount;
            this.content = content;
            this.matched = matched;
        }

        String getTransactionId() { return transactionId; }
        boolean isMatched() { return matched; }
    }
}
