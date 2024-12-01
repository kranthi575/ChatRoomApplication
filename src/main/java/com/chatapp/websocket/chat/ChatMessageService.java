package com.chatapp.websocket.chat;

import com.chatapp.websocket.chatroom.ChatRoomRepository;
import com.chatapp.websocket.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatRepository chatRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage saveChatMessage(ChatMessage chatMessage) {

        var chatId=chatRoomService.getChatRoomId(
                chatMessage.getSenderId(),chatMessage.getRecipientId(),true)
                .orElseThrow(); //we can create our own exception

        chatMessage.setChatId(chatId);
        return chatRepository.save(chatMessage);

    }

    public List<ChatMessage> findChatMessages(String senderId,String recipientId) {

        var chatId=chatRoomService.getChatRoomId(senderId,recipientId,false);


        return chatId.map(chatRepository::findByChatId).orElse(new ArrayList<>());


    }
}
