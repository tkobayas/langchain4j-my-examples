package org.example.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ElapsedTimeExtension.class)
class OllamaChatModelTest {

    @Test
    void simple_example() {

        ChatLanguageModel model = OllamaChatModel.builder()
                .baseUrl(Constants.BASE_URL)
                .modelName(Constants.MODEL_NAME)
                .build();

        String answer = model.generate("Provide 3 short bullet points explaining why Java is awesome");

        System.out.println(answer);
    }

    @Test
    void json_output_example() {

        ChatLanguageModel model = OllamaChatModel.builder()
                .baseUrl(Constants.BASE_URL)
                .modelName(Constants.MODEL_NAME)
                .format("json")
                .build();

        String json = model.generate("Give me a JSON with 2 fields: name and age of a John Doe, 42");

        System.out.println(json);
    }
}
