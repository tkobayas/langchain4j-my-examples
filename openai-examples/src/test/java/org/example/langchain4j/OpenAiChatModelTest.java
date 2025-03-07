package org.example.langchain4j;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;

import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;

import static dev.langchain4j.data.message.UserMessage.userMessage;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

class OpenAiChatModelTest {

    @Test
    void simple_example() {

        ChatLanguageModel chatModel = OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName(OpenAiChatModelName.GPT_4_O_MINI)
                .temperature(0.3)
                .logRequests(true)
                .logResponses(true)
                .build();

        String response = chatModel.chat("パソコンで音楽を作るクラブの名前は何がいいですか？");

        System.out.println(response);
    }

    @Test
    void conversation() {

        ChatLanguageModel chatModel = OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName(OpenAiChatModelName.GPT_4_O_MINI)
                .temperature(0.3)
                .logRequests(true)
                .logResponses(true)
                .build();

        UserMessage firstUserMessage = UserMessage.from("Hello, my name is Klaus");
        AiMessage firstAiMessage = chatModel.chat(firstUserMessage).aiMessage(); // Hi Klaus, how can I help you?

        System.out.println(firstAiMessage.text());

        UserMessage secondUserMessage = UserMessage.from("What is my name?");
        AiMessage secondAiMessage = chatModel.chat(firstUserMessage, firstAiMessage, secondUserMessage).aiMessage(); // Klaus

        System.out.println(secondAiMessage.text());
    }

    @Test
    void chatMemory() {

        ChatLanguageModel chatModel = OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName(OpenAiChatModelName.GPT_4_O_MINI)
                .temperature(0.3)
                .build();

        ChatMemory chatMemory = TokenWindowChatMemory.withMaxTokens(1000, new OpenAiTokenizer(OpenAiChatModelName.GPT_4_O_MINI));

        SystemMessage systemMessage = SystemMessage.from("あなたはラーメンが大好きなグルメブロガーです。豚骨ラーメンが特に好きです。");
        chatMemory.add(systemMessage);

        UserMessage userMessage1 = userMessage("どの種類のラーメンがおすすめですか？");
        chatMemory.add(userMessage1);
        System.out.println("[User]: " + userMessage1.singleText());

        ChatResponse response1 = chatModel.chat(chatMemory.messages());
        AiMessage aiMessage1 = response1.aiMessage();
        System.out.println("[LLM]: " + aiMessage1.text());
        chatMemory.add(aiMessage1);

        System.out.println("----");
        UserMessage userMessage2 = userMessage("どこへ行けばそのラーメンが食べられますか？");
        chatMemory.add(userMessage2);
        System.out.println("[User]: " + userMessage2.singleText());

        ChatResponse response2 = chatModel.chat(chatMemory.messages());
        AiMessage aiMessage2 = response2.aiMessage();
        System.out.println("[LLM]: " + aiMessage2.text());
    }

    interface Ramenholic {

        @dev.langchain4j.service.SystemMessage("あなたはラーメンが大好きなグルメブロガーです。豚骨ラーメンが特に好きです。")
        String answer(String question);
    }

    @Test
    void aiService() {
        ChatLanguageModel chatModel = OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName(OpenAiChatModelName.GPT_4_O_MINI)
                .temperature(0.3)
                .build();

        Ramenholic ramenholic = AiServices.create(Ramenholic.class, chatModel);

        String answer = ramenholic.answer("どの種類のラーメンがおすすめですか？");
        System.out.println(answer);
        System.out.println("----");

        answer = ramenholic.answer("どこへ行けばそのラーメンが食べられますか？");
        System.out.println(answer);
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
