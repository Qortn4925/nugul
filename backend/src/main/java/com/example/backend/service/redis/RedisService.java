package com.example.backend.service.redis;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

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

    public Map<String, Map<String, String>> getReadAtMapForRooms(List<Integer> roomIds) {
        Map<String, Map<String, String>> result = new HashMap<>();
        for (Integer roomId : roomIds) {
            String key = "chat:readAt:" + roomId;
            Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
            if (!map.isEmpty()) {
                Map<String, String> stringMap = map.entrySet().stream()
                        .collect(Collectors.toMap(
                                e -> e.getKey().toString(),
                                e -> e.getValue().toString()
                        ));
                result.put(roomId.toString(), stringMap);
            }
        }
        return result;
    }

    // 메시지 timestamp 리스트 가져오기
    public List<String> getMessageTimestamps(String roomId) {
        String key = "chat:messageTimestamps:" + roomId;
        return redisTemplate.opsForList().range(key, 0, -1)
                .stream().map(Object::toString).toList();
    }
}
