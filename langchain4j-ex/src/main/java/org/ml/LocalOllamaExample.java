package org.ml;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import java.time.Duration;

public class LocalOllamaExample {

    static void main() {

        ChatModel model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("gemma4:12b")
                .timeout(Duration.ofSeconds(120))
                .build();

        String answer = model.chat("why is the sky blue?");
        System.out.println(answer);
    }


}
