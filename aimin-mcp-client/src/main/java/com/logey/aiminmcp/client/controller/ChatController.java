package com.logey.aiminmcp.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatClient chatClient;

    @RequestMapping("/qa")
    public  String qa(String msg){
        return chatClient.prompt(msg).call().content();
    }

}
