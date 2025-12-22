package com.example.backend.service;

import com.example.backend.entity.Review;
import com.example.backend.entity.User;
import com.example.backend.repository.ReviewRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import com.example.backend.dto.ReviewUpdateDTO;
import com.example.backend.dto.ReviewDTO;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    public Review createReview(ReviewDTO review) {
        Review reviewEntity = new Review();
        User trainee = userRepository.findById(review.getTraineeId()).filter(user -> user.isTrainee())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Trainee"));
        User trainer = userRepository.findById(review.getTrainerId()).filter(user -> user.isTrainer())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Trainer"));
        reviewEntity.setComment(review.getComment());
        reviewEntity.setRating(review.getRating());
        reviewEntity.setReviewDate(new Date()); // auto set current date
        reviewEntity.setUser(trainee);
        reviewEntity.setTrainer(trainer);
        return reviewRepository.save(reviewEntity);
    }

   
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

 
    public List<Review> getReviewsByTrainer(Integer trainerId) {
        return reviewRepository.findByTrainerId(trainerId);
    }

   
    public Review updateReview(Integer id, ReviewUpdateDTO reviewDetails) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Review"));
        
        existingReview.setComment(reviewDetails.getComment());
        existingReview.setRating(reviewDetails.getRating());
        existingReview.setReviewDate(new Date());
        // not updating user and trainer to keep data integrity
        
        return reviewRepository.save(existingReview);
    }


    public void deleteReview(Integer id) {
        reviewRepository.deleteById(id);
    }
}
