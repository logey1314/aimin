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
    public String qa(String msg){
        return chatClient.prompt(msg)
                .system("根据用户需求，调用函数获取表名，再次调用函数获取表的字段信息。根据获取的表名和 sql，最后返回查询结果。")
                .call().content();
    }

}
