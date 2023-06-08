package com.github.mishchuk7.ecomednpbot.v1.service;

import com.github.mishchuk7.ecomednpbot.v1.model.UserSession;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserSessionService {

    private final Map<Long, UserSession> userSessionMap = new HashMap<>();

    public UserSession getUserSession(Long chatId) {
        return userSessionMap.getOrDefault(chatId, UserSession
                .builder()
                .chatId(chatId)
                .build());
    }

    public UserSession saveUserSession(Long chatId, UserSession userSession) {
        return userSessionMap.put(chatId, userSession);
    }
}
