package com.example.backend.service;

import com.example.backend.dto.ChatMessageDTO;
import com.example.backend.dto.ChatPartnerDTO;
import com.example.backend.dto.ConversationDTO;
import com.example.backend.dto.ConversationResponseDTO;
import com.example.backend.dto.MessageDTO;
import com.example.backend.entity.Conversation;
import com.example.backend.entity.Message;
import com.example.backend.entity.User;
import com.example.backend.repository.ConversationRepository;
import com.example.backend.repository.MessageRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Lấy hoặc tạo cuộc trò chuyện giữa 2 người dùng
     * Cho phép tạo conversation trước khi book lịch
     */
    @Transactional
    public Conversation getOrCreateConversation(Integer userId1, Integer userId2) {
        Optional<Conversation> existingConversation = 
            conversationRepository.findByUsers(userId1, userId2);
        
        if (existingConversation.isPresent()) {
            return existingConversation.get();
        }

        User user1 = userRepository.findById(userId1)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng 1"));
        User user2 = userRepository.findById(userId2)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng 2"));

        Conversation conversation = new Conversation();
        conversation.setTrainee(user1);
        conversation.setTrainer(user2);
        
        return conversationRepository.save(conversation);
    }

    /**
     * Gửi tin nhắn từ người dùng này đến người dùng khác
     * Cho phép nhắn tin trước khi book lịch
     */
    @Transactional
    public Message sendMessage(ChatMessageDTO dto) {
        User sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người gửi"));
        
        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người nhận"));

        // Lấy hoặc tạo conversation
        Conversation conversation = getOrCreateConversation(dto.getSenderId(), dto.getReceiverId());

        // Tạo tin nhắn mới
        Message message = new Message();
        message.setConversation(conversation);
        message.setSender(sender);
        message.setContent(dto.getContent());
        message.setMessageType(dto.getMessageType() != null ? dto.getMessageType() : "TEXT");
        message.setMediaUrl(dto.getMediaUrl());

        Message savedMessage = messageRepository.save(message);

        // Cập nhật thông tin conversation
        conversation.setLastMessageContent(dto.getContent());
        conversation.setLastMessageAt(LocalDateTime.now());
        conversationRepository.save(conversation);

        return savedMessage;
    }

    /**
     * Lấy danh sách tin nhắn trong một cuộc trò chuyện
     */
    public List<MessageDTO> getMessages(Long conversationId) {
        List<Message> messages = messageRepository.findByConversationIdOrderBySendAtAsc(conversationId);
        List<MessageDTO> result = new ArrayList<>();
        
        for (Message msg : messages) {
            MessageDTO dto = new MessageDTO();
            dto.setId(msg.getId());
            dto.setConversationId(msg.getConversation().getId());
            dto.setSenderId(msg.getSender().getId());
            dto.setSenderName(msg.getSender().getFullName());
            dto.setContent(msg.getContent());
            dto.setMessageType(msg.getMessageType());
            dto.setSendAt(msg.getSendAt());
            dto.setRead(msg.isRead());
            dto.setMediaUrl(msg.getMediaUrl());
            result.add(dto);
        }
        
        return result;
    }

    /**
     * Lấy lịch sử trò chuyện giữa 2 người dùng
     */
    public List<MessageDTO> getConversation(Integer userId1, Integer userId2) {
        Optional<Conversation> conversation = conversationRepository.findByUsers(userId1, userId2);
        if (conversation.isPresent()) {
            return getMessages(conversation.get().getId());
        }
        return new ArrayList<>();
    }

    /**
     * Lấy danh sách cuộc trò chuyện của user với thông tin chi tiết
     */
    public List<ConversationDTO> getConversationList(Integer userId) {
        List<Conversation> conversations = conversationRepository.findByUserId(userId);
        List<ConversationDTO> result = new ArrayList<>();

        for (Conversation conv : conversations) {
            User partner = conv.getTrainee().getId().equals(userId) ? conv.getTrainer() : conv.getTrainee();
            Long unreadCount = messageRepository.countUnreadInConversation(conv.getId(), userId);

            ConversationDTO dto = new ConversationDTO();
            dto.setId(conv.getId());
            dto.setPartnerId(partner.getId());
            dto.setPartnerName(partner.getFullName());
            dto.setPartnerRole(partner.getRole());
            dto.setLastMessageContent(conv.getLastMessageContent());
            dto.setLastMessageAt(conv.getLastMessageAt());
            dto.setUnreadCount(unreadCount);

            result.add(dto);
        }

        return result;
    }

    /**
     * Lấy danh sách người đã nhắn tin với user
     */
    public List<ChatPartnerDTO> getChatPartners(Integer userId) {
        List<Conversation> conversations = conversationRepository.findByUserId(userId);
        List<ChatPartnerDTO> result = new ArrayList<>();
        
        for (Conversation conv : conversations) {
            User partner = conv.getTrainee().getId().equals(userId) ? conv.getTrainer() : conv.getTrainee();
            Long unreadCount = messageRepository.countUnreadInConversation(conv.getId(), userId);
            
            ChatPartnerDTO dto = new ChatPartnerDTO();
            dto.setUserId(partner.getId());
            dto.setFullName(partner.getFullName());
            dto.setRole(partner.getRole());
            dto.setConversationId(conv.getId());
            dto.setLastMessageContent(conv.getLastMessageContent());
            dto.setLastMessageAt(conv.getLastMessageAt());
            dto.setUnreadCount(unreadCount);
            
            result.add(dto);
        }
        
        return result;
    }

    /**
     * Đánh dấu tin nhắn đã đọc trong một cuộc trò chuyện
     */
    @Transactional
    public void markAsRead(Long conversationId, Integer userId) {
        List<Message> unreadMessages = messageRepository.findUnreadMessages(conversationId, userId);
        
        for (Message message : unreadMessages) {
            message.setRead(true);
            messageRepository.save(message);
        }
    }

    /**
     * Đánh dấu tin nhắn đã đọc giữa 2 người dùng
     */
    @Transactional
    public void markAsReadByUsers(Integer receiverId, Integer senderId) {
        Optional<Conversation> conversation = conversationRepository.findByUsers(receiverId, senderId);
        if (conversation.isPresent()) {
            markAsRead(conversation.get().getId(), receiverId);
        }
    }

    /**
     * Đếm số tin nhắn chưa đọc
     */
    public Long countUnreadMessages(Integer userId) {
        return messageRepository.countTotalUnread(userId);
    }

    /**
     * Bắt đầu cuộc trò chuyện mới (người tập có thể nhắn tin PT trước khi book)
     */
    @Transactional
    public ConversationResponseDTO startConversation(Integer userId1, Integer userId2) {
        Conversation conv = getOrCreateConversation(userId1, userId2);
        
        ConversationResponseDTO dto = new ConversationResponseDTO();
        dto.setId(conv.getId());
        dto.setTraineeId(conv.getTrainee().getId());
        dto.setTraineeName(conv.getTrainee().getFullName());
        dto.setTrainerId(conv.getTrainer().getId());
        dto.setTrainerName(conv.getTrainer().getFullName());
        dto.setLastMessageContent(conv.getLastMessageContent());
        dto.setLastMessageAt(conv.getLastMessageAt());
        
        return dto;
    }
}
