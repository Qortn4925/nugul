package com.example.backend.service.chat;

import com.example.backend.dto.chat.ChatMessage;
import com.example.backend.dto.chat.ChatRoom;
import com.example.backend.mapper.chat.ChatMapper;
import com.example.backend.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    @Value("${image.src.prefix}")
    String imageSrcPrefix;

    @Value("${bucket.name}")
    String bucketName;

    private final ChatMapper mapper;

    private  final RedisService redisService;
    private final SimpMessagingTemplate messagingTemplate;

    public boolean creatChatRoom(ChatRoom chatRoom) {
        int cnt = mapper.createChatRoom(chatRoom);

        return cnt == 1;
    }

    public ChatRoom chatRoomView(String roomId, String memberId) {


        ChatRoom chatRoom = mapper.chatRoomViewById(roomId, memberId);

        return chatRoom;
    }


    public List<ChatRoom> chatRoomList(String memberId, String type) {

        List<ChatRoom> chatRoomList = mapper.chatRoomListByMemberId(memberId, type);

        List<Integer> roomIds= chatRoomList.stream().map(ChatRoom::getRoomId).toList();

        // redis
        Map<String, String> lastMessages = redisService.getLastMessages(roomIds);
        Map<String, String> readAtMap=redisService.getReadAtMapForRooms(roomIds,memberId);
        for (ChatRoom room : chatRoomList) {
            // 마지막 메시지
            String convertRoomId = String.valueOf(room.getRoomId());
            room.setLastMessage(lastMessages.get(convertRoomId));
            // 안읽은 메시지 수 계산
            String roomReadAt = readAtMap.get(convertRoomId);
            if (roomReadAt != null) {
                long unReadCount=redisService.getCountUnReadMessage(convertRoomId, roomReadAt);
                log.info(unReadCount + "unReadCoutn");
                room.setUnreadCount((int) unReadCount);
            } else {
                room.setUnreadCount(0);
            }
        }

        return chatRoomList;
    }


    public void insertMessage(ChatMessage chatMessage) {
        redisService.updateLastMessage(chatMessage.getRoomId(),chatMessage.getContent());
        redisService.saveMessageTimestamp(chatMessage.getRoomId());
        mapper.insertMessage(chatMessage);

        // 상대한테 이벤트 발송
       redisService.notifyUnreadCountToOtherUsers(chatMessage.getRoomId(),chatMessage.getSender(),chatMessage.getContent());

    }


    public Integer findChatRoomId(ChatRoom chatRoom) {

        return mapper.findChatRoomId(chatRoom);
    }


    //초기  메시지 로딩
    public List<ChatMessage> getMessageById(String roomId) {

        return mapper.initialChatMessageByRoomId(roomId);
    }

    // 이전 메시지 로딩
    public List<ChatMessage> getPreviousMessageBySentAt(String roomId, LocalDateTime sentAtTime) {

        return mapper.previousChatMessageBySentAt(roomId, sentAtTime);
    }


    public String getImage(String memberId) {
        String profileImage = mapper.getProfileImage(memberId);
        String mainImageUrl;

        mainImageUrl = String.format(STR."\{imageSrcPrefix}/profile/\{memberId}/\{profileImage}");
        return mainImageUrl;
    }

    // 이거 false임 삭제 안한상태로 초기화
    public boolean updateDeletedTrue(int roomId, String buyer) {
        int cnt = mapper.updateDeletedTrue(roomId, buyer);

        return cnt == 1;
    }

    // 채팅방 나가기
    public boolean exitChatRoom(String roomId, String memberId) {

       int cnt = mapper.updateDeltedByRoomIdAndMemberId(roomId, memberId);

       return  cnt ==1;
    }

    // redis에  읽은 시간 정보 업데이트
    public void saveReadAtToRedis(String roomId, String memberId) {
        redisService.updateReadAt(roomId, memberId);
        long unreadCount = 0L;
        String lastMessage=redisService.getLastMessage(roomId);

        Map<String, Object> payload = Map.of(
                "roomId", roomId,
                "lastMessage", lastMessage,
                "unreadCount", unreadCount
        );
        messagingTemplate.convertAndSendToUser(memberId, "/queue/room-updates", payload);
    }
}
