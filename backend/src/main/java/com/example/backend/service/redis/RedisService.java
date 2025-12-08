package com.example.backend.service.redis;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final SimpMessagingTemplate messagingTemplate;

    public void updateReadAt(String roomId, String memberId) {
        String readAtKey = "chat:readAt:" + roomId;
        String now = Instant.now().toString();
        redisTemplate.opsForHash().put(readAtKey, memberId, now);
    }


    public Map<Object, Object> getReadAtMap(String roomId) {
        String key = "chat:readAt:" + roomId;
        return redisTemplate.opsForHash().entries(key);
    }

    public Set<String> getAllReadAtKeys() {
         return redisTemplate.keys("chat:readAt:*");
    }

    public void updateLastMessage(String roomId, String content) {
        String key = "chat:lastMessage:" + roomId;
        redisTemplate.opsForValue().set(key, content);
    }

    public String getLastMessage(String roomId) {
        String key = "chat:lastMessage:" + roomId;
        Object result = redisTemplate.opsForValue().get(key);
        return result != null ? result.toString() : "";
    }

    public void saveMessageTimestamp(String roomId) {
        String key = "chat:messages:" + roomId;
        String tempId = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis();
        redisTemplate.opsForZSet().add(key, tempId, timestamp);
    }

    public Map<String, String> getLastMessages(List<Integer> roomIds) {
        Map<String,String> map = new HashMap<>();
        for(Integer roomId:roomIds){
            String key = "chat:lastMessage:" + roomId;
            Object msg = redisTemplate.opsForValue().get(key);

            if(msg != null) map.put(roomId.toString(),msg.toString());
        }
        return map;
    }

    public  Map<String, String> getReadAtMapForRooms(List<Integer> roomIds ,String memberId) {
        Map<String, String> result = new HashMap<>();
        for (Integer roomId : roomIds) {
            String convertId= String.valueOf(roomId);

            String key = "chat:readAt:" + convertId;
            Object readAt = redisTemplate.opsForHash().get(key, memberId);

            if (readAt != null) {
                result.put(String.valueOf(roomId), readAt.toString());
            }
        }
        return result;
    }

    // 메시지 timestamp 리스트 가져오기
    public Long  getCountUnReadMessage(String roomId,String readAt) {
        String key = "chat:messages:" + roomId;
        // Redis의 리스트 길이
        double readTimestamp = Instant.parse(readAt).toEpochMilli();
        // readAt 이후의 모든 메시지 개수
        Long count = redisTemplate.opsForZSet().count(key, readTimestamp, Double.POSITIVE_INFINITY);

        return count != null ? count : 0L;
    }

    public void notifyUnreadCountToOtherUsers(String roomId, String sender, String content) {
        Map<Object, Object> readAtMap = getReadAtMap(roomId);
        readAtMap.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(sender)) // 내 아이디 제외
                .forEach(entry -> {
                    String otherUserId = entry.getKey().toString();
                    String otherReadAt = entry.getValue().toString();
                    Long unreadCount = getCountUnReadMessage(roomId, otherReadAt);

                    Map<String, Object> payload = Map.of(
                            "roomId", roomId,
                            "lastMessage", content,
                            "unreadCount", unreadCount
                    );
                    messagingTemplate.convertAndSendToUser(otherUserId ,"/queue/room-updates",payload);
                });

     }
}
