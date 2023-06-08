package com.github.mishchuk7.ecomednpbot.v1;

import com.github.mishchuk7.ecomednpbot.v1.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


@Slf4j
@Component
public class EcomedNpBot extends TelegramLongPollingBot {

    private final Dispatcher dispatcher;

    @Value("${BOT_NAME}")
    private String botUsername;

    protected EcomedNpBot(Dispatcher dispatcher, @Value("${BOT_TOKEN}") String botToken) {
        super(new DefaultBotOptions(), botToken);
        this.dispatcher = dispatcher;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = getUpdateId(update);
        assert chatId != null;

        if (update.hasMessage() && update.getMessage().hasText()) {
            String textFromUser = update.getMessage().getText();
            Long userId = update.getMessage().getChatId();
            chatId = update.getMessage().getChatId();
            String userFirstName = update.getMessage().getFrom().getFirstName();
            log.info("[{}, {}] : {}", userId, userFirstName, textFromUser);
        }
        UserRequest userRequest = UserRequest.builder()
                .update(update)
                .chatId(chatId)
                .build();

        boolean dispatched = dispatcher.dispatch(userRequest);
        if (!dispatched) {
            log.warn("Unexpected update from user");
        }

    }

    private Long getUpdateId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        }
        return null;
    }

}
