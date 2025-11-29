package com.example.backend.Interceptor;

import com.example.backend.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class MyChannelInterceptor implements ChannelInterceptor {

    private final ChatService chatService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        Map<String, Object> sessionAttributes = accessor.getSessionAttributes();

        String memberId = accessor.getFirstNativeHeader("memberId");
        String roomId= accessor.getFirstNativeHeader("roomId");

        if (command == StompCommand.SUBSCRIBE) {
            if(roomId!=null && memberId!=null){
                sessionAttributes.put("currentRoomId",roomId);
                sessionAttributes.put("memberId",memberId);
                chatService.updateReadAt(roomId, memberId);
            }
            log.info(" 구독 감지: command={}, roomId={}, memberId={}", command, roomId, memberId);
        } else if( command == StompCommand.UNSUBSCRIBE) {
             String storedRoomId= (String) sessionAttributes.get("roomId");
             String storedMemberId= (String) sessionAttributes.get("memberId");
              if(storedRoomId!=null && storedMemberId != null){
                  log.info(" 구독 해제 감지: command={}, roomId={}, memberId={}", command, roomId, memberId);
                  chatService.updateReadAt(roomId, memberId);
                  sessionAttributes.remove("currentRoomId");
              }
        }

        return  message;
    }
}
