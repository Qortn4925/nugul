package com.example.backend.service.redis;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

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
}
