package com.logey.aimin.ai.config;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ChatClientConfig {

    private final ChatStorageMemory chatMemory;

    @Bean
    public ChatClient chatClient (OllamaChatModel clientModel) {
//        ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
        MessageChatMemoryAdvisor chatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        ChatClient chatClient = ChatClient.builder(clientModel)
                .defaultAdvisors(chatMemoryAdvisor)
                .build();
        return chatClient;
    }

}
