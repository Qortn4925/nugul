package com.example.backend.Scheduler;

import com.example.backend.mapper.chat.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;


@RequiredArgsConstructor
public class ChatFlushScheduler {

    private final ChatMapper mapper;

    private final RedisTemplate<String, Object> redisTemplate;



    @Scheduled(fixedRate = 60000)
    public void flushReadAtToDB () {

    }
}
