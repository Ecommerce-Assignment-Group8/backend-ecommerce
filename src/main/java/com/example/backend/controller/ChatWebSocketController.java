package com.example.backend.controller;

import com.example.backend.dto.ChatMessageDTO;
import com.example.backend.entity.Message;
import com.example.backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatService chatService;

    /**
     * Xử lý tin nhắn real-time qua WebSocket
     * Client gửi tin nhắn đến: /app/chat.send
     * Server broadcast đến: /queue/messages/{userId}
     */
    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessageDTO dto) {
        // Lưu tin nhắn vào database
        Message savedMessage = chatService.sendMessage(dto);
        
        // Gửi tin nhắn đến người nhận qua WebSocket
        messagingTemplate.convertAndSend(
            "/queue/messages/" + dto.getReceiverId(), 
            savedMessage
        );
        
        // Gửi xác nhận lại cho người gửi
        messagingTemplate.convertAndSend(
            "/queue/messages/" + dto.getSenderId(), 
            savedMessage
        );
    }

    /**
     * Thông báo đang gõ tin nhắn
     * Client gửi đến: /app/chat.typing
     */
    @MessageMapping("/chat.typing")
    public void typing(@Payload ChatMessageDTO dto) {
        messagingTemplate.convertAndSend(
            "/queue/typing/" + dto.getReceiverId(),
            dto.getSenderId()
        );
    }
}
