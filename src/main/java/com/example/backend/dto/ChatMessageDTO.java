package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {
    private Integer senderId;
    private Integer receiverId;
    private String content;
    private String messageType;
    private String mediaUrl;
}
