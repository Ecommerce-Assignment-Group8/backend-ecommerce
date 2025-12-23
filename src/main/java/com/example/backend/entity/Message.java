package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Column(name = "message_type")
    private String messageType = "text";

    @Column(name = "send_at", nullable = false)
    private LocalDateTime sendAt;

    @Column(name = "is_read")
    private boolean isRead = false;

    @Column(name = "media_url")
    private String mediaUrl;

    @PrePersist
    protected void onCreate() {
        sendAt = LocalDateTime.now();
        isRead = false;
        if (messageType == null) {
            messageType = "text";
        }
    }
}
