package com.logey.aimin.ai.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private  final ChatClient chatClient;
    private final ChatMemory chatMemory;

    @GetMapping("/ai")
    public String  generateChatClient(@RequestParam(value="message") String message){
        return chatClient.prompt(message)
                .advisors( advisor -> advisor.param(ChatMemory.CONVERSATION_ID, "1"))
                .call().content();
    }

}
