package com.chatapp.websocket.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public void saveUser(User user) {

        user.setStatus(Status.ONLINE);
        userRepository.save(user);

    }

    public void disconnectUser(User user) {

        User storedUser=userRepository.findById(user.getNickname())
                .orElse(null);

        if(storedUser!=null) {
            storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }

    }

    public List<User> findConnectedUsers() {

        return userRepository.findAllByStatus(Status.ONLINE);

    }

}
