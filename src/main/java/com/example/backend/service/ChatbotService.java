package com.example.backend.service;

import com.example.backend.dto.ChatbotRequestDTO;
import com.example.backend.dto.ChatbotResponseDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ChatbotService {

    @Value("${gemini.api.key:your_gemini_api_key_here}")
    private String geminiApiKey;

    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";

    private final OkHttpClient httpClient;
    private final Gson gson;

    // Câu trả lời mẫu cho các câu hỏi phổ biến (fallback khi không có API key)
    private final Map<String, String> predefinedAnswers;

    public ChatbotService() {
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        this.gson = new Gson();
        this.predefinedAnswers = initPredefinedAnswers();
    }

    private Map<String, String> initPredefinedAnswers() {
        Map<String, String> answers = new HashMap<>();
        
        answers.put("tập gym mua gì", 
            "🏋️ **Đồ cần mua khi tập gym:**\n\n" +
            "1. **Quần áo tập**: Áo thun thoáng mát, quần short hoặc legging co giãn\n" +
            "2. **Giày tập**: Giày sneaker đế phẳng, hỗ trợ tốt\n" +
            "3. **Bình nước**: Bình giữ nhiệt 500ml-1L\n" +
            "4. **Khăn tập**: Khăn microfiber thấm mồ hôi\n" +
            "5. **Găng tay tập** (tùy chọn): Bảo vệ tay khi nâng tạ\n" +
            "6. **Đai lưng** (tùy chọn): Hỗ trợ khi squat, deadlift\n" +
            "7. **Whey Protein** (tùy chọn): Bổ sung protein sau tập\n\n" +
            "💡 Tip: Bắt đầu với những thứ cơ bản, sau đó mua thêm theo nhu cầu!");

        answers.put("pt nào tốt",
            "👨‍🏫 **Cách chọn PT (Personal Trainer) tốt:**\n\n" +
            "1. **Chứng chỉ chuyên môn**: Tìm PT có chứng chỉ từ các tổ chức uy tín (ACE, NASM, ISSA...)\n" +
            "2. **Kinh nghiệm**: PT có ít nhất 2-3 năm kinh nghiệm\n" +
            "3. **Chuyên môn phù hợp**: Chọn PT chuyên về mục tiêu của bạn (giảm cân, tăng cơ, thể hình...)\n" +
            "4. **Review từ học viên**: Đọc đánh giá từ những người đã tập\n" +
            "5. **Buổi tập thử**: Thử 1 buổi để xem phong cách huấn luyện\n" +
            "6. **Giao tiếp tốt**: PT biết lắng nghe và điều chỉnh theo bạn\n\n" +
            "💡 Trên hệ thống của chúng tôi, bạn có thể xem profile, đánh giá và nhắn tin trực tiếp với PT trước khi đặt lịch!");

        answers.put("giảm cân",
            "🔥 **Hướng dẫn giảm cân hiệu quả:**\n\n" +
            "1. **Chế độ ăn**: Thâm hụt 300-500 calo/ngày\n" +
            "2. **Cardio**: 30-45 phút, 3-5 lần/tuần\n" +
            "3. **Tập tạ**: Duy trì cơ bắp, đốt mỡ hiệu quả\n" +
            "4. **Uống nước**: 2-3 lít nước/ngày\n" +
            "5. **Ngủ đủ**: 7-8 tiếng mỗi đêm\n\n" +
            "💡 Tip: Giảm 0.5-1kg/tuần là tốc độ lành mạnh!");

        answers.put("tăng cơ",
            "💪 **Hướng dẫn tăng cơ:**\n\n" +
            "1. **Protein**: 1.6-2.2g/kg thể trọng/ngày\n" +
            "2. **Tập tạ**: 4-6 lần/tuần, progressive overload\n" +
            "3. **Nghỉ ngơi**: 48h giữa các nhóm cơ\n" +
            "4. **Calorie thặng dư**: 300-500 calo/ngày\n" +
            "5. **Ngủ**: 7-9 tiếng để phục hồi\n\n" +
            "💡 Tip: Kiên trì 3-6 tháng để thấy kết quả rõ rệt!");

        answers.put("lịch tập",
            "📅 **Gợi ý lịch tập cho người mới:**\n\n" +
            "**Tuần tập 3 buổi:**\n" +
            "- Thứ 2: Ngực + Vai + Tay sau\n" +
            "- Thứ 4: Lưng + Tay trước\n" +
            "- Thứ 6: Chân + Bụng\n\n" +
            "**Tuần tập 4 buổi:**\n" +
            "- Thứ 2: Ngực\n" +
            "- Thứ 3: Lưng\n" +
            "- Thứ 5: Vai + Tay\n" +
            "- Thứ 7: Chân\n\n" +
            "💡 Tip: Đặt lịch với PT để có chương trình phù hợp nhất!");

        return answers;
    }

    /**
     * Xử lý câu hỏi từ người dùng
     */
    public ChatbotResponseDTO chat(ChatbotRequestDTO request) {
        String userMessage = request.getMessage().toLowerCase().trim();

        // Kiểm tra câu trả lời có sẵn trước
        String predefinedAnswer = findPredefinedAnswer(userMessage);
        if (predefinedAnswer != null) {
            return new ChatbotResponseDTO(predefinedAnswer, true, null);
        }

        // Nếu có API key, gọi Gemini API
        if (geminiApiKey != null && !geminiApiKey.equals("your_gemini_api_key_here") && !geminiApiKey.isEmpty()) {
            return callGeminiApi(request.getMessage());
        }

        // Fallback response
        return new ChatbotResponseDTO(
            "🤖 Xin chào! Tôi là trợ lý ảo của FitConnect.\n\n" +
            "Bạn có thể hỏi tôi về:\n" +
            "- \"Tập gym mua gì?\"\n" +
            "- \"PT nào tốt?\"\n" +
            "- \"Cách giảm cân\"\n" +
            "- \"Cách tăng cơ\"\n" +
            "- \"Lịch tập gym\"\n\n" +
            "Hoặc nhắn tin trực tiếp với PT để được tư vấn chi tiết hơn!",
            true, null
        );
    }

    private String findPredefinedAnswer(String message) {
        for (Map.Entry<String, String> entry : predefinedAnswers.entrySet()) {
            if (message.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    private ChatbotResponseDTO callGeminiApi(String userMessage) {
        try {
            String systemPrompt = "Bạn là trợ lý ảo của ứng dụng FitConnect - nền tảng kết nối người tập gym với huấn luyện viên cá nhân (PT). " +
                    "Hãy trả lời ngắn gọn, hữu ích về các chủ đề: tập gym, dinh dưỡng, chọn PT, thiết bị tập. " +
                    "Trả lời bằng tiếng Việt, sử dụng emoji phù hợp.";

            JsonObject requestBody = new JsonObject();
            JsonArray contents = new JsonArray();
            
            JsonObject content = new JsonObject();
            JsonArray parts = new JsonArray();
            
            JsonObject part = new JsonObject();
            part.addProperty("text", systemPrompt + "\n\nCâu hỏi: " + userMessage);
            parts.add(part);
            
            content.add("parts", parts);
            contents.add(content);
            requestBody.add("contents", contents);

            RequestBody body = RequestBody.create(
                    gson.toJson(requestBody),
                    MediaType.parse("application/json")
            );

            Request request = new Request.Builder()
                    .url(GEMINI_API_URL + "?key=" + geminiApiKey)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            try (Response response = httpClient.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();
                    JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);
                    
                    String text = jsonResponse
                            .getAsJsonArray("candidates")
                            .get(0).getAsJsonObject()
                            .getAsJsonObject("content")
                            .getAsJsonArray("parts")
                            .get(0).getAsJsonObject()
                            .get("text").getAsString();
                    
                    return new ChatbotResponseDTO(text, true, null);
                } else {
                    return new ChatbotResponseDTO(null, false, 
                            "Lỗi khi gọi API: " + response.code());
                }
            }
        } catch (IOException e) {
            return new ChatbotResponseDTO(null, false, 
                    "Lỗi kết nối: " + e.getMessage());
        } catch (Exception e) {
            return new ChatbotResponseDTO(null, false, 
                    "Lỗi xử lý: " + e.getMessage());
        }
    }
}
