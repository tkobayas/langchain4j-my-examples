package org.example.langchain4j;

import java.util.concurrent.CompletableFuture;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;

class OllamaStreamingChatModelTest {

    @Test
    void streaming_example() {

        StreamingChatLanguageModel model = OllamaStreamingChatModel.builder()
                .baseUrl(Constants.BASE_URL)
                .modelName(Constants.MODEL_NAME)
                .temperature(0.0)
                .build();

        String userMessage = "Write a 100-word poem about Java and AI";

        long start = System.currentTimeMillis();
        CompletableFuture<Response<AiMessage>> futureResponse = new CompletableFuture<>();
        model.generate(userMessage, new StreamingResponseHandler<AiMessage>() {

            @Override
            public void onNext(String token) {
                System.out.print(token);
            }

            @Override
            public void onComplete(Response<AiMessage> response) {
                futureResponse.complete(response);
            }

            @Override
            public void onError(Throwable error) {
                futureResponse.completeExceptionally(error);
            }
        });

        futureResponse.join();
        System.out.println("Time: " + (System.currentTimeMillis() - start) + " ms");
    }
}
