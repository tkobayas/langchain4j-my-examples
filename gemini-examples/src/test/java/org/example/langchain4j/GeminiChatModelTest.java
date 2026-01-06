package org.example.langchain4j;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import org.junit.jupiter.api.Test;

class GeminiChatModelTest {

    @Test
    void simple_example() {

        var chatModel = GoogleAiGeminiChatModel.builder()
                .apiKey(EnvVars.GOOGLE_AI_GEMINI_API_KEY)
                .modelName("gemini-2.5-flash-lite")
                .temperature(0.3)
                .logRequests(true)
                .logResponses(true)
                .build();

        String response = chatModel.chat("パソコンで音楽を作るクラブの名前は何がいいですか？");

        System.out.println(response);
    }
}
