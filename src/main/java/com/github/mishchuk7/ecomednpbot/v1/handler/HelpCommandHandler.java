package com.github.mishchuk7.ecomednpbot.v1.handler;

import com.github.mishchuk7.ecomednpbot.v1.enums.TrackingStatusColor;
import com.github.mishchuk7.ecomednpbot.v1.model.UserRequest;
import com.github.mishchuk7.ecomednpbot.v1.service.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class HelpCommandHandler extends UserRequestHandler {

    private static final String HELP = "/help";

    private final TelegramService telegramService;


    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(), HELP);
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        String help = """
                  Як користуватися ботом:
                  
                - для початку пошуку просто відправте боту
                  номер телефону або прізвище отримувача;
                  
                - номер телефону можна вводити
                  в будь-якому форматі, наприклад:
                  <i>+38(066)1234455, (067)1234556,
                  067 123-45-46, 380971234556</i> і т.п.;
                  
                - <i><b>"Детальна інформація"</b></i> -
                  інформація про знайдене відправлення:
                  номер, статус, тип,кіл-ть місць, вага,
                  вартість доставки,платник за доставку
                  і т.д.;
                                
                - <i><b>"Перелік відправлень"</b></i> - це перелік
                  з 10 останніх відправлень здійснених за
                  відповідним номером телефону або пріз-
                  вищем.
                  Щоб дізнатися більш детально про якесь
                  відправлення ви можете скопіювати його
                  номер та відправити боту.
                  
                  <i><b>Статуси відправлень</b></i>:
                """;
        String blue = TrackingStatusColor.BLUE.getColor();
        String orange = TrackingStatusColor.ORANGE.getColor();
        String green = TrackingStatusColor.GREEN.getColor();
        String grey = TrackingStatusColor.GREY.getColor();
        String red = TrackingStatusColor.RED.getColor();
        String colors = blue + " У дорозі"
                + "\n" + orange + " Прибув у відділення"
                + "\n" + green + " Отримано"
                + "\n" + grey + " Готується до відправлення"
                + "\n" + red + " Відмова отримувача";
        telegramService.sendMessage(dispatchRequest.getChatId(), help + colors);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
