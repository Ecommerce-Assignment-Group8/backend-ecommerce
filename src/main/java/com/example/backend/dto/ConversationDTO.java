package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDTO {
    private Long id;
    private Integer partnerId;
    private String partnerName;
    private String partnerRole;
    private String lastMessageContent;
    private LocalDateTime lastMessageAt;
    private Long unreadCount;
}
