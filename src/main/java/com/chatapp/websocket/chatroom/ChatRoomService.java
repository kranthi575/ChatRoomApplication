package com.chatapp.websocket.chatroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatRoomId(
            String senderId,String recipientId,boolean createChatRoomIfNotExists
    ){

        return chatRoomRepository.findBySenderIdAndRecipientId(senderId,recipientId)
                .map(ChatRoom::getChatId)
                .or(()->{

                    if(createChatRoomIfNotExists){
                        var chatId=createChatId(senderId,recipientId);

                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });

    }

    private String createChatId(String senderId, String recipientId) {

        var chatId=String.format("%s_%s", senderId, recipientId);  //hunter_john

        ChatRoom SenderRecipient=ChatRoom
                .builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        ChatRoom RecipientSender= ChatRoom.builder()
                .chatId(chatId)
                .recipientId(recipientId)
                .senderId(senderId)
                .build();


        chatRoomRepository.save(SenderRecipient);
        chatRoomRepository.save(RecipientSender);

        return chatId;

    }

}
