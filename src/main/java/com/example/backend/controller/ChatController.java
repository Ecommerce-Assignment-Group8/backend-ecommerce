package com.example.backend.controller;

import com.example.backend.dto.ChatMessageDTO;
import com.example.backend.dto.ChatPartnerDTO;
import com.example.backend.dto.ConversationDTO;
import com.example.backend.dto.ConversationResponseDTO;
import com.example.backend.dto.MessageDTO;
import com.example.backend.entity.Message;
import com.example.backend.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
@Tag(name = "Chat API", description = "API trò chuyện trực tuyến giữa người tập và PT")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    @Operation(summary = "Gửi tin nhắn", description = "Gửi tin nhắn từ người dùng này đến người dùng khác (có thể nhắn trước khi book lịch)")
    public ResponseEntity<Message> sendMessage(@RequestBody ChatMessageDTO dto) {
        Message message = chatService.sendMessage(dto);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/start")
    @Operation(summary = "Bắt đầu cuộc trò chuyện", description = "Tạo cuộc trò chuyện mới giữa người tập và PT (có thể nhắn trước khi book lịch)")
    public ResponseEntity<ConversationResponseDTO> startConversation(
            @RequestParam Integer userId1,
            @RequestParam Integer userId2) {
        ConversationResponseDTO conversation = chatService.startConversation(userId1, userId2);
        return ResponseEntity.ok(conversation);
    }

    @GetMapping("/conversation")
    @Operation(summary = "Lấy lịch sử trò chuyện", description = "Lấy tất cả tin nhắn giữa 2 người dùng")
    public ResponseEntity<List<MessageDTO>> getConversation(
            @RequestParam Integer userId1,
            @RequestParam Integer userId2) {
        List<MessageDTO> messages = chatService.getConversation(userId1, userId2);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/conversations/{userId}")
    @Operation(summary = "Lấy danh sách cuộc trò chuyện", description = "Lấy tất cả cuộc trò chuyện của user với thông tin chi tiết")
    public ResponseEntity<List<ConversationDTO>> getConversationList(@PathVariable Integer userId) {
        List<ConversationDTO> conversations = chatService.getConversationList(userId);
        return ResponseEntity.ok(conversations);
    }

    @GetMapping("/messages/{conversationId}")
    @Operation(summary = "Lấy tin nhắn trong cuộc trò chuyện", description = "Lấy tất cả tin nhắn trong một cuộc trò chuyện")
    public ResponseEntity<List<MessageDTO>> getMessages(@PathVariable Long conversationId) {
        List<MessageDTO> messages = chatService.getMessages(conversationId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/partners/{userId}")
    @Operation(summary = "Lấy danh sách người đã nhắn tin", description = "Lấy danh sách tất cả người đã từng nhắn tin với user")
    public ResponseEntity<List<ChatPartnerDTO>> getChatPartners(@PathVariable Integer userId) {
        List<ChatPartnerDTO> partners = chatService.getChatPartners(userId);
        return ResponseEntity.ok(partners);
    }

    @PostMapping("/mark-read")
    @Operation(summary = "Đánh dấu đã đọc", description = "Đánh dấu tất cả tin nhắn từ một người gửi là đã đọc")
    public ResponseEntity<Map<String, String>> markAsRead(
            @RequestParam Integer receiverId,
            @RequestParam Integer senderId) {
        chatService.markAsReadByUsers(receiverId, senderId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Đã đánh dấu tin nhắn là đã đọc");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mark-read/{conversationId}")
    @Operation(summary = "Đánh dấu đã đọc theo conversation", description = "Đánh dấu tất cả tin nhắn trong cuộc trò chuyện là đã đọc")
    public ResponseEntity<Map<String, String>> markConversationAsRead(
            @PathVariable Long conversationId,
            @RequestParam Integer userId) {
        chatService.markAsRead(conversationId, userId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Đã đánh dấu tin nhắn là đã đọc");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/unread-count/{userId}")
    @Operation(summary = "Đếm tin nhắn chưa đọc", description = "Lấy số lượng tin nhắn chưa đọc của user")
    public ResponseEntity<Map<String, Long>> countUnreadMessages(@PathVariable Integer userId) {
        Long count = chatService.countUnreadMessages(userId);
        Map<String, Long> response = new HashMap<>();
        response.put("unreadCount", count);
        return ResponseEntity.ok(response);
    }
}
