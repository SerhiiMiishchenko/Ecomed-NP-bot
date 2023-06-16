package com.github.mishchuk7.ecomednpbot.v1.handler;

import com.github.mishchuk7.ecomednpbot.v1.model.UserRequest;
import com.github.mishchuk7.ecomednpbot.v1.service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartCommandHandler extends UserRequestHandler {

    private static final String START = "/start";

    private final TelegramService telegramService;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(), START);
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        telegramService.sendMessage(dispatchRequest.getChatId(),
                "Вітаю! Цей бот може надати вам інформацію про відправлення Нової Пошти в яких відправником або одержувачем є ТОВ Екомед.\n" +
                        "Для пошуку введіть номер телефону або прізвище отримувача ⤵️");
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
