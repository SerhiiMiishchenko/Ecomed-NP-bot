package com.github.mishchuk7.ecomednpbot.v1.service;

import com.github.mishchuk7.ecomednpbot.v1.sender.EcomedNpBotSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TelegramService {

    private final EcomedNpBotSender ecomedNpBotSender;

    public TelegramService(EcomedNpBotSender ecomedNpBotSender) {
        this.ecomedNpBotSender = ecomedNpBotSender;
    }

    public void sendMessage(Long chatId, String text) {
        sendMessage(chatId, text, null);
    }

    public void sendMessage(Long chatId, String text, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .parseMode(ParseMode.HTML)
                .replyMarkup(inlineKeyboardMarkup)
                .build();
        execute(message);
    }

    private void execute(BotApiMethod botApiMethod) {
        try {
            ecomedNpBotSender.execute(botApiMethod);
        } catch (TelegramApiException e) {
            log.error("Exception: ", e);
        }
    }

}
