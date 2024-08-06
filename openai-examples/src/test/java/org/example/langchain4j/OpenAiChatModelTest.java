package org.example.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import org.junit.jupiter.api.Test;

class OpenAiChatModelTest {

    @Test
    void simple_example() {

        ChatLanguageModel model = OpenAiChatModel.withApiKey("demo");

        String joke = model.generate("Tell me a joke about Java");

        System.out.println(joke);
    }

}
