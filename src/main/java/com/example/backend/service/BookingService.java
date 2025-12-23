package com.example.backend.service;

import com.example.backend.entity.Booking;
import com.example.backend.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.backend.dto.BookingCreateDTO;
import com.example.backend.entity.User;
import com.example.backend.entity.Package;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.PackageRepository;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    private @Autowired UserRepository userRepository;
    private @Autowired PackageRepository packageRepository;

    public Booking createBooking(BookingCreateDTO bookingCreateDTO) {
        Booking booking = new Booking();

        User trainee = userRepository.findById(bookingCreateDTO.getTraineeId()).filter(user -> user.isTrainee())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Trainee"));

        // 2. Tìm package từ ID
        Package pkg = packageRepository.findById(bookingCreateDTO.getPackageId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Package"));

        booking.setTrainee(trainee);
        booking.setBookingPackage(pkg);
        booking.setDate(bookingCreateDTO.getDate());
        booking.setTotalAmount(bookingCreateDTO.getTotalAmount());
        booking.setStatus(Booking.Status.PENDING); // Mặc định là PENDING khi mới tạo
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByTraineeId(Integer traineeId) {
        return bookingRepository.findByTraineeId(traineeId);
    }

    public Booking updateStatus(Integer id, Booking.Status newStatus) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Booking"));
        booking.setStatus(newStatus);
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
    }
}