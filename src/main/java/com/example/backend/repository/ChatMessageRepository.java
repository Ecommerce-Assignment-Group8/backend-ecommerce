package com.example.backend.repository;

import com.example.backend.entity.ChatMessage;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    
    // Lấy tất cả tin nhắn giữa 2 người dùng, sắp xếp theo thời gian
    @Query("SELECT m FROM ChatMessage m WHERE " +
           "(m.sender.id = :userId1 AND m.receiver.id = :userId2) OR " +
           "(m.sender.id = :userId2 AND m.receiver.id = :userId1) " +
           "ORDER BY m.sentAt ASC")
    List<ChatMessage> findConversation(@Param("userId1") Integer userId1, 
                                        @Param("userId2") Integer userId2);

    // Lấy danh sách người đã nhắn tin với user
    @Query("SELECT DISTINCT CASE WHEN m.sender.id = :userId THEN m.receiver ELSE m.sender END " +
           "FROM ChatMessage m WHERE m.sender.id = :userId OR m.receiver.id = :userId")
    List<User> findChatPartners(@Param("userId") Integer userId);

    // Đếm số tin nhắn chưa đọc
    @Query("SELECT COUNT(m) FROM ChatMessage m WHERE m.receiver.id = :userId AND m.isRead = false")
    Long countUnreadMessages(@Param("userId") Integer userId);

    // Lấy tin nhắn chưa đọc từ một người gửi cụ thể
    List<ChatMessage> findByReceiverIdAndSenderIdAndIsReadFalse(Integer receiverId, Integer senderId);
}
