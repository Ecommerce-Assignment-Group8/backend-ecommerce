package com.example.backend.controller;

import com.example.backend.entity.Review;
import com.example.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.dto.ReviewDTO;
import com.example.backend.dto.ReviewUpdateDTO;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // create review
    @PostMapping
    public Review create(@RequestBody ReviewDTO review) {
        return reviewService.createReview(review);
    }

    // get all reviews
    @GetMapping
    public List<Review> getAll() {
        return reviewService.getAllReviews();
    }

    // get reviews by trainer
    @GetMapping("/trainer/{trainerId}")
    public List<Review> getByTrainer(@PathVariable Integer trainerId) {
        return reviewService.getReviewsByTrainer(trainerId);
    }

    // update review
    @PutMapping("/{id}")
    public ResponseEntity<Review> update(@PathVariable Integer id, @RequestBody ReviewUpdateDTO review) {
        return ResponseEntity.ok(reviewService.updateReview(id, review));
    }

    // delete review
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Xóa review thành công!");
    }
}