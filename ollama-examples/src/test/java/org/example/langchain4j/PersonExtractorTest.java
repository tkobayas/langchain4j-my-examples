package org.example.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ElapsedTimeExtension.class)
class PersonExtractorTest {

//    @Test
//    void extractPerson() {
//
//        ChatLanguageModel model = OllamaChatModel.builder()
//                .baseUrl(Constants.BASE_URL)
//                .modelName(Constants.MODEL_NAME)
//                .build();
//
//        long start = System.currentTimeMillis();
//        String answer = model.generate("Provide 3 short bullet points explaining why Java is awesome");
//
//        System.out.println(answer);
//        System.out.println("Time: " + (System.currentTimeMillis() - start) + " ms");
//    }

}
