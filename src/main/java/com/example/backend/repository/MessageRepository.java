package com.example.backend.repository;

import com.example.backend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
    List<Message> findByConversationIdOrderBySendAtAsc(Long conversationId);

    @Query("SELECT COUNT(m) FROM Message m WHERE m.conversation.id = :conversationId " +
           "AND m.sender.id != :userId AND m.isRead = false")
    Long countUnreadInConversation(@Param("conversationId") Long conversationId, 
                                    @Param("userId") Integer userId);

    @Query("SELECT COUNT(m) FROM Message m WHERE " +
           "(m.conversation.trainee.id = :userId OR m.conversation.trainer.id = :userId) " +
           "AND m.sender.id != :userId AND m.isRead = false")
    Long countTotalUnread(@Param("userId") Integer userId);

    @Query("SELECT m FROM Message m WHERE m.conversation.id = :conversationId " +
           "AND m.sender.id != :userId AND m.isRead = false")
    List<Message> findUnreadMessages(@Param("conversationId") Long conversationId, 
                                      @Param("userId") Integer userId);
}
