package com.logey.aimin.ai.service;

import com.logey.aimin.ai.docment.Msg;
import com.logey.aimin.ai.repository.MsgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MsgService {

    private final MongoTemplate mongoTemplate;

    public List<Msg> findByConversationId(String conversationId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("conversationId").is(conversationId));
        return mongoTemplate.find(query, Msg.class);
    }

}
