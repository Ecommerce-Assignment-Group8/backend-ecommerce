package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationResponseDTO {
    private Long id;
    private Integer traineeId;
    private String traineeName;
    private Integer trainerId;
    private String trainerName;
    private String lastMessageContent;
    private LocalDateTime lastMessageAt;
}
