package com.chatapp.websocket.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

@EnableMongoRepositories
public interface UserRepository  extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
