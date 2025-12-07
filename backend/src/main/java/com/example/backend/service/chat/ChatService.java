package com.example.backend.service.chat;

import com.example.backend.dto.chat.ChatMessage;
import com.example.backend.dto.chat.ChatRoom;
import com.example.backend.mapper.chat.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    @Value("${image.src.prefix}")
    String imageSrcPrefix;

    @Value("${bucket.name}")
    String bucketName;

    private final ChatMapper mapper;

    private final RedisTemplate<String, Object> redisTemplate;

    public boolean creatChatRoom(ChatRoom chatRoom) {
        int cnt = mapper.createChatRoom(chatRoom);

        return cnt == 1;
    }

    public ChatRoom chatRoomView(String roomId, String memberId) {


        ChatRoom chatRoom = mapper.chatRoomViewById(roomId, memberId);

        return chatRoom;
    }


    public List<ChatRoom> chatRoomList(String memberId, String type) {
        //db 수정해야함??
        // 상태 가져오기
        List<ChatRoom> chatRoomList = mapper.chatRoomListByMemberId(memberId, type);


        return chatRoomList;
    }

    public void insertMessage(ChatMessage chatMessage) {

        mapper.insertMessage(chatMessage);
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



    public void saveReadAtToRedis(String roomId, String memberId) {
        System.out.println(" 12312312" );
        String key ="read_at" +roomId ;
        String now =    LocalDateTime.now().toString();
        // redis에  시간 업데이트 하기
        redisTemplate.opsForHash().put(key,memberId,now);

        System.out.println("redisTemplate = " + redisTemplate);

    }
}
