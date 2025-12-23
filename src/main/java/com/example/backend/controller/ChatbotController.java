package com.example.backend.controller;

import com.example.backend.dto.ChatbotRequestDTO;
import com.example.backend.dto.ChatbotResponseDTO;
import com.example.backend.service.ChatbotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
@Tag(name = "Chatbot API", description = "API trợ lý ảo AI trả lời câu hỏi về gym")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/ask")
    @Operation(summary = "Hỏi chatbot", 
               description = "Gửi câu hỏi và nhận câu trả lời từ AI chatbot. Ví dụ: 'Tập gym mua gì?', 'PT nào tốt?'")
    public ResponseEntity<ChatbotResponseDTO> ask(@RequestBody ChatbotRequestDTO request) {
        ChatbotResponseDTO response = chatbotService.chat(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    @Operation(summary = "Kiểm tra trạng thái", 
               description = "Kiểm tra trạng thái hoạt động của chatbot")
    public ResponseEntity<ChatbotResponseDTO> health() {
        ChatbotResponseDTO response = new ChatbotResponseDTO(
            "🤖 Chatbot đang hoạt động! Hãy hỏi tôi về gym nhé.",
            true, null
        );
        return ResponseEntity.ok(response);
    }
}
