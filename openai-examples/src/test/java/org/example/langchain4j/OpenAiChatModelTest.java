package org.example.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import org.junit.jupiter.api.Test;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

class OpenAiChatModelTest {

    @Test
    void simple_example() {

        ChatLanguageModel chatModel = OpenAiChatModel.builder()
                .apiKey(ApiKeys.OPENAI_API_KEY)
                .modelName(GPT_4_O_MINI)
                .build();

        String joke = chatModel.chat("Tell me a joke about Java");

        System.out.println(joke);
    }

    @Test
    void generateDrl() {

        ChatLanguageModel model = OpenAiChatModel.builder()
                .apiKey(ApiKeys.OPENAI_API_KEY)
                .modelName(GPT_4_O_MINI)
                .build();

        String drl = model.chat("Write DRL rules for Drools. " +
                                            "If a person is a child, then they are not eligible to vote. If a person is not a child, then they are eligible to vote." +
                                            "`eligible to vote` is a flag in Person class. Modify it in RHS of the rule" +
                                            "Don't include any other description. Write DRL rules only.");

        System.out.println(drl);
    }
}
