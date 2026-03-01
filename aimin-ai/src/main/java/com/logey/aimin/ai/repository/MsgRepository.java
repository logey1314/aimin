package com.logey.aimin.ai.repository;

import com.logey.aimin.ai.docment.Msg;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MsgRepository extends MongoRepository<Msg, String> {
}
