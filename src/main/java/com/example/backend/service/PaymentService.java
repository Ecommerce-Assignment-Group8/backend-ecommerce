package com.example.backend.service;

import com.example.backend.dto.PaymentRequestDTO;
import com.example.backend.dto.PaymentResponseDTO;
import com.example.backend.entity.Booking;
import com.example.backend.entity.Payment;
import com.example.backend.entity.User;
import com.example.backend.repository.BookingRepository;
import com.example.backend.repository.PaymentRepository;
import com.example.backend.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentService {

    @Value("${stripe.api.key:sk_test_your_test_key_here}")
    private String stripeApiKey;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    /**
     * Tạo phiên thanh toán Stripe Checkout
     */
    @Transactional
    public PaymentResponseDTO createCheckoutSession(PaymentRequestDTO request) {
        try {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

            Booking booking = null;
            if (request.getBookingId() != null) {
                booking = bookingRepository.findById(request.getBookingId())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy booking"));
            }

            // Tạo Stripe Checkout Session
            SessionCreateParams.Builder paramsBuilder = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(request.getSuccessUrl() != null ? 
                            request.getSuccessUrl() : "http://localhost:3000/payment/success?session_id={CHECKOUT_SESSION_ID}")
                    .setCancelUrl(request.getCancelUrl() != null ? 
                            request.getCancelUrl() : "http://localhost:3000/payment/cancel")
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency(request.getCurrency() != null ? 
                                                            request.getCurrency().toLowerCase() : "vnd")
                                                    .setUnitAmount(request.getAmount())
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName(request.getDescription() != null ? 
                                                                            request.getDescription() : "Thanh toán dịch vụ PT")
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .build()
                    )
                    .putMetadata("user_id", String.valueOf(user.getId()));

            if (booking != null) {
                paramsBuilder.putMetadata("booking_id", String.valueOf(booking.getId()));
            }

            Session session = Session.create(paramsBuilder.build());

            // Lưu thông tin payment vào database
            Payment payment = new Payment();
            payment.setUser(user);
            payment.setBooking(booking);
            payment.setAmount(request.getAmount());
            payment.setCurrency(request.getCurrency() != null ? request.getCurrency() : "VND");
            payment.setStatus(Payment.PaymentStatus.PENDING);
            payment.setPaymentMethod(Payment.PaymentMethod.STRIPE);
            payment.setStripeSessionId(session.getId());

            paymentRepository.save(payment);

            PaymentResponseDTO response = new PaymentResponseDTO();
            response.setPaymentId(payment.getId());
            response.setSessionId(session.getId());
            response.setCheckoutUrl(session.getUrl());
            response.setStatus("PENDING");
            response.setMessage("Chuyển hướng đến trang thanh toán Stripe");

            return response;

        } catch (StripeException e) {
            PaymentResponseDTO errorResponse = new PaymentResponseDTO();
            errorResponse.setStatus("ERROR");
            errorResponse.setMessage("Lỗi khi tạo phiên thanh toán: " + e.getMessage());
            return errorResponse;
        }
    }

    /**
     * Xử lý callback sau khi thanh toán thành công
     */
    @Transactional
    public PaymentResponseDTO handlePaymentSuccess(String sessionId) {
        try {
            Session session = Session.retrieve(sessionId);
            
            Payment payment = paymentRepository.findByStripeSessionId(sessionId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin thanh toán"));

            if ("complete".equals(session.getStatus()) || "paid".equals(session.getPaymentStatus())) {
                payment.setStatus(Payment.PaymentStatus.COMPLETED);
                payment.setStripePaymentIntentId(session.getPaymentIntent());
                paymentRepository.save(payment);

                // Cập nhật trạng thái booking nếu có
                if (payment.getBooking() != null) {
                    Booking booking = payment.getBooking();
                    booking.setStatus(Booking.Status.FINISHED);
                    bookingRepository.save(booking);
                }

                PaymentResponseDTO response = new PaymentResponseDTO();
                response.setPaymentId(payment.getId());
                response.setSessionId(sessionId);
                response.setStatus("COMPLETED");
                response.setMessage("Thanh toán thành công!");
                return response;
            } else {
                PaymentResponseDTO response = new PaymentResponseDTO();
                response.setPaymentId(payment.getId());
                response.setSessionId(sessionId);
                response.setStatus(session.getPaymentStatus());
                response.setMessage("Trạng thái thanh toán: " + session.getPaymentStatus());
                return response;
            }

        } catch (StripeException e) {
            PaymentResponseDTO errorResponse = new PaymentResponseDTO();
            errorResponse.setStatus("ERROR");
            errorResponse.setMessage("Lỗi khi xác nhận thanh toán: " + e.getMessage());
            return errorResponse;
        }
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
}
