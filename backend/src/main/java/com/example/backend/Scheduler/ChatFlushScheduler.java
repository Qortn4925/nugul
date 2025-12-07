package com.example.backend.Scheduler;

import com.example.backend.mapper.chat.ChatMapper;
import com.example.backend.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class ChatFlushScheduler {

    private final ChatMapper chatMapper;

    private final RedisService redisService;


    @Scheduled(fixedRate = 60000)
    public void flushReadAtToDB () {
        Set<String> allReadAtKeys = redisService.getAllReadAtKeys();
        for(String key: allReadAtKeys){
            String roomId=key.replace("chat:readAt:","");
            Map<Object, Object> readAtMap = redisService.getReadAtMap(roomId);
                if(readAtMap.isEmpty()) continue;

                for(Map.Entry<Object, Object> entry: readAtMap.entrySet()){
                    String memberId= (String) entry.getKey();
                    String readAt= (String) entry.getValue();
                    Instant instant =Instant.parse(readAt);
                    LocalDateTime UTCReadAt = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
                    chatMapper.updateReadAt(roomId,memberId, UTCReadAt);
                }
        }
    }
}
