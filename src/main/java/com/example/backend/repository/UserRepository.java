package com.example.backend.repository;

import com.example.backend.entity.User;
import com.example.backend.entity.User.Gender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // other functions can be defined here as needed
    List<User> findByRole(String role);

    User findByEmail(String email);

    User findByEmailAndRole(String email, String role);

    @Query("SELECT u FROM User u WHERE " +
            "u.role = 'TRAINER' AND " +
            "(:fullName IS NULL OR LOWER(CAST(u.fullName AS text)) LIKE LOWER(CONCAT('%', CAST(:fullName AS text), '%'))) AND "
            +
            "(:gender IS NULL OR u.gender = :gender) AND " +
            "(:specialty IS NULL OR LOWER(CAST(u.specialty AS text)) = LOWER(CAST(:specialty AS text))) AND " +
            "(:minExp IS NULL OR u.experienceYear >= :minExp)")
    List<User> searchTrainers(
            @Param("fullName") String fullName,
            @Param("gender") Gender gender,
            @Param("specialty") String specialty,
            @Param("minExp") Integer minExp);
}
