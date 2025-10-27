package com.example.backend.chat;

import com.example.backend.dto.chat.ChatMessage;
import com.example.backend.service.chat.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
public class MessagePerformanceTesting {

    @Autowired
    private ChatService chatService;

    @Test
    public void testPaginationPerformance() {
        // 페이지네이션 성능 측정
        int[] offsets = {100, 1000, 5000, 10000,50000,90000};
        for( int  offset: offsets) {
            long start = System.currentTimeMillis();
            List<ChatMessage> messageById = chatService.getMessageById("82", offset);
            long end = System.currentTimeMillis();
            System.out.println("페이지네이션 쿼리 실행 시간 "+offset +" offset, 시간 "+ (end - start) + "ms");
        }
    }
}