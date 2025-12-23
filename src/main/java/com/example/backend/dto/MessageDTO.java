package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private Long id;
    private Long conversationId;
    private Integer senderId;
    private String senderName;
    private String content;
    private String messageType;
    private LocalDateTime sendAt;
    private boolean isRead;
    private String mediaUrl;
}
