package com.logey.aimin.ai.config;

import com.logey.aimin.ai.docment.Msg;
import com.logey.aimin.ai.repository.MsgRepository;
import com.logey.aimin.ai.service.MsgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class ChatStorageMemory implements ChatMemory {

    private final MsgRepository msgRepository;

    private final MsgService msgService;

    @Override
    public void add(String conversationId, List<Message> messages) {
        for (Message message : messages) {
            Msg msg= new Msg(conversationId,message);
            log.info("add msg: {}, {}", message.getMessageType(),msg.getText());
            msgRepository.save(msg);
        }

    }

    @Override
    public List<Message> get(String conversationId) {
        List<Msg> list = msgService.findByConversationId(conversationId);
        return list.stream().map(Msg::toMessage).toList();
    }

    @Override
    public void clear(String conversationId) {

    }
}
