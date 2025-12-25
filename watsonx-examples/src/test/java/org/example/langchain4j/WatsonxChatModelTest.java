package org.example.langchain4j;

import com.ibm.watsonx.ai.CloudRegion;
import dev.langchain4j.model.watsonx.WatsonxChatModel;
import org.junit.jupiter.api.Test;

class WatsonxChatModelTest {

    @Test
    void simple_example() {

        var chatModel = WatsonxChatModel.builder()
                .baseUrl(CloudRegion.TOKYO)
                .apiKey(EnvVars.WATSONX_API_KEY)
                .modelName(EnvVars.MODEL_ID)
                .projectId(EnvVars.PROJECT_ID)
                .temperature(0.3)
                .logRequests(true)
                .logResponses(true)
                .build();

        String response = chatModel.chat("パソコンで音楽を作るクラブの名前は何がいいですか？");

        System.out.println(response);
    }
}
