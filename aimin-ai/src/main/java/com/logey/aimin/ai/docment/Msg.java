package com.logey.aimin.ai.docment;

import lombok.Data;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("chat")
public class Msg {
    private String text;
    private String conversationId;
    private Date date;
    private String type;
    public Msg(String conversationId, Message message) {
        this.conversationId = conversationId;
        this.text= message.getText();
        this.date=new Date();
        this.type=message.getMessageType().getValue();

    }
    public static Message toMessage(Msg msg) {
        // 使用 switch 表达式根据消息类型创建不同的 Message 实例
        return switch (msg.getType()) {
            case "user" -> new UserMessage(msg.getText());      // 用户消息类型
            case "assistant" -> new AssistantMessage(msg.getText());  // 助手消息类型
            case "system" -> new SystemMessage(msg.getText());  // 系统消息类型
            // 默认分支：抛出异常而非返回 null，避免空指针风险
            default -> null;
        };
    }
}
