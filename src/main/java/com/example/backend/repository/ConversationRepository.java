package com.example.backend.repository;

import com.example.backend.entity.Conversation;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    
    @Query("SELECT c FROM Conversation c WHERE " +
           "(c.trainee.id = :traineeId AND c.trainer.id = :trainerId) OR " +
           "(c.trainee.id = :trainerId AND c.trainer.id = :traineeId)")
    Optional<Conversation> findByUsers(@Param("traineeId") Integer traineeId, 
                                        @Param("trainerId") Integer trainerId);

    @Query("SELECT c FROM Conversation c WHERE c.trainee.id = :userId OR c.trainer.id = :userId " +
           "ORDER BY c.lastMessageAt DESC")
    List<Conversation> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT CASE WHEN c.trainee.id = :userId THEN c.trainer ELSE c.trainee END " +
           "FROM Conversation c WHERE c.trainee.id = :userId OR c.trainer.id = :userId")
    List<User> findChatPartners(@Param("userId") Integer userId);
}
