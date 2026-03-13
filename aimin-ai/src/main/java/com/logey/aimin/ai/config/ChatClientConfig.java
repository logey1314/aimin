package com.logey.aimin.ai.config;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ChatClientConfig {

    private final ChatStorageMemory chatMemory;
    private final VectorStore vectorStore;

    @Bean
    public ChatClient chatClient (OllamaChatModel clientModel) {
//        ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
        MessageChatMemoryAdvisor chatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        ChatClient chatClient = ChatClient.builder(clientModel)
                .defaultAdvisors(
                        chatMemoryAdvisor,
                        new QuestionAnswerAdvisor(vectorStore))
                .build();
        return chatClient;

//        ChatResponse response = ChatClient.builder(chatModel)
//                .build().prompt()
//                .advisors(new QuestionAnswerAdvisor(vectorStore))
//                .user(userText)
//                .call()
//                .chatResponse();
    }

}
