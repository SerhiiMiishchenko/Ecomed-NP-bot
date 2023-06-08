package com.github.mishchuk7.ecomednpbot.v1.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static com.github.mishchuk7.ecomednpbot.v1.enums.SearchButton.DETAILED_SEARCH;
import static com.github.mishchuk7.ecomednpbot.v1.enums.SearchButton.SHIPPING_LIST;

@Slf4j
@Component
public class KeyboardHelper {

    public InlineKeyboardMarkup buildSearchMenu(String callbackData) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton(DETAILED_SEARCH.getValue());
        InlineKeyboardButton button2 = new InlineKeyboardButton(SHIPPING_LIST.getValue());
        button1.setCallbackData(callbackData + ":" + DETAILED_SEARCH);
        button2.setCallbackData(callbackData + ":" + SHIPPING_LIST);
        keyboard.add(List.of(button1, button2));
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

}
