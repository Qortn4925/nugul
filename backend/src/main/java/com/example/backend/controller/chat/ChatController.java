package com.example.backend.controller.chat;


import com.example.backend.dto.chat.ChatMessage;
import com.example.backend.dto.chat.ChatRoom;
import com.example.backend.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;



    @MessageMapping("/{roomId}") // send/{roomId} 이렇게 넘어오는거임
    @SendTo("/room/{roomId}")
    public ChatMessage handleChatMessage(@DestinationVariable String roomId, ChatMessage chatMessage) {

        // 보낸 메시지 저장시킬 방 번호 입력
        chatMessage.setRoomId(roomId);
        chatService.insertMessage(chatMessage);
        return chatMessage;
    }

    @PostMapping("create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Integer> createChatRoom(@RequestBody ChatRoom chatRoom) {

        // 방이 있는지 먼저 확인   없으면 만들기
        Integer roomId = chatService.findChatRoomId(chatRoom);


        if (roomId == null) {
            chatService.creatChatRoom(chatRoom);
            return ResponseEntity.ok().body(chatRoom.getRoomId());
        } else {
            chatService.updateDeletedTrue(roomId, chatRoom.getBuyer());
            chatRoom.setRoomId(roomId);
            return ResponseEntity.ok().body(chatRoom.getRoomId());
        }


    }

    @GetMapping("view/{roomId}")
    @PreAuthorize("isAuthenticated()")
    public ChatRoom chatRoomView(@PathVariable String roomId,
                                 @RequestParam String memberId,
                                 @RequestParam(value = "page", defaultValue = "1") String page
    ) {

        //  chatroom 정보 조회
        ChatRoom chatRoom = chatService.chatRoomView(roomId, memberId);
        // 해당 채팅방의 메시지 정보 조회  , page
        return chatRoom;

    }

    @GetMapping("view/{roomId}/messages")
    @PreAuthorize("isAuthenticated()")
    public List<ChatMessage> getMessage(@PathVariable String roomId,
                                        @RequestParam(required = false) String sentAt) {

        // 초기 메시지
        if (sentAt == null) {
            return chatService.getMessageById(roomId);
        }
        // offset 없는 시간 처리
        LocalDateTime ldt = LocalDateTime.parse(sentAt); // "2025-11-27T17:55:33"
        Instant sent = ldt.toInstant(ZoneOffset.UTC);    // UTC 기준 Instant 변환
            return chatService.getPreviousMessageBySentAt(roomId,ldt);


    }


    @GetMapping("list")
    @PreAuthorize("isAuthenticated()")
    public List<ChatRoom> chatRoomList(@RequestParam(value = "memberId") String memberId,
                                       @RequestParam(value = "type", defaultValue = "all") String type ) {

        return chatService.chatRoomList(memberId, type);

    }


    @PostMapping("post/{roomId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Map<String, Object>> exitChatRoom(@PathVariable String roomId,
                                                              @RequestParam String memberId) {

        System.out.println("roomId = " + roomId);
        System.out.println("memberId = " + memberId);
        boolean exited = chatService.exitChatRoom(roomId, memberId);

        if(exited) {
            return ResponseEntity.ok().body(Map.of("message", Map.of("type", "success", "content", " 채팅방에서 나갔습니다.")));
        }else {
            return ResponseEntity.badRequest().body(Map.of("message", Map.of("type", "warning", "content", "존재하지 않는 채팅방 입니다.")));
        }
    }


    @GetMapping("{roomId}/image")
    public String getImage(@RequestParam String memberId) {
        return chatService.getImage(memberId);
    }
    // 구독과, 구독 해제시  redis에 시간 정보를 업데이트
    @MessageMapping("/chat/updateReadAt")
    public void updateReadAt( ChatMessage chatMessage) {
        chatService.saveReadAtToRedis(chatMessage.getRoomId(),chatMessage.getSender());

    }
}
