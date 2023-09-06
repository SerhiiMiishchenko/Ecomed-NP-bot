package com.github.mishchuk7.ecomednpbot.v1.exception;

public class EcomedNpTelegramBotException extends RuntimeException {

    public EcomedNpTelegramBotException() {
    }

    public EcomedNpTelegramBotException(String message) {
        super(message);
    }

    public EcomedNpTelegramBotException(String message, Throwable cause) {
        super(message, cause);
    }
}
