package com.example.backend.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Component
public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        // 쿼리파라미터 또는 헤더에서 userId 추출
        String userId = extractUserId(request); //
        return () -> userId; //
    }

    private String extractUserId(ServerHttpRequest request) {
        String query = request.getURI().getQuery(); //
        if(query != null && query.contains("userId=")) {
            return query.split("userId=")[1];
        }
        return "anonymous-" + UUID.randomUUID();
    }
}
