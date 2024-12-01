package com.chatapp.websocket.chat;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableMongoRepositories
public interface ChatRepository extends MongoRepository<ChatMessage, String> {

    List<ChatMessage> findByChatId(String s);
}
