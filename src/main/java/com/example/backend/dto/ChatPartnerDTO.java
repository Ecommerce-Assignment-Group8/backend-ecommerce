package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatPartnerDTO {
    private Integer userId;
    private String fullName;
    private String role;
    private Long conversationId;
    private String lastMessageContent;
    private LocalDateTime lastMessageAt;
    private Long unreadCount;
}
