package com.example.backend.repository;

import com.example.backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // get all reviews for a specific trainer
    List<Review> findByTrainerId(Integer trainerId);

    // get all reviews written by a specific user
    List<Review> findByUserId(Integer userId);
}