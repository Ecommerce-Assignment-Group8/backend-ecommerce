package com.example.backend.controller;

import com.example.backend.entity.Booking;
import com.example.backend.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.backend.dto.BookingCreateDTO;



@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody BookingCreateDTO bookingCreateDTO) {
        return ResponseEntity.ok(bookingService.createBooking(bookingCreateDTO));
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAll() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // API update status of Booking
    @PatchMapping("/{id}/status")
    public ResponseEntity<Booking> updateStatus(
            @PathVariable Integer id, 
            @RequestParam Booking.Status status) {
        return ResponseEntity.ok(bookingService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Đã xóa lịch đặt thành công");
    }
}
