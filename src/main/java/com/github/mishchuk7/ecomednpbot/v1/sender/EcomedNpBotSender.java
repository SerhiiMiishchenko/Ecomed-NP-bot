package com.github.mishchuk7.ecomednpbot.v1.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Slf4j
@Component
public class EcomedNpBotSender extends DefaultAbsSender {

    protected EcomedNpBotSender(@Value("${BOT_TOKEN}")String botToken) {
        super(new DefaultBotOptions(), botToken);
    }

}
