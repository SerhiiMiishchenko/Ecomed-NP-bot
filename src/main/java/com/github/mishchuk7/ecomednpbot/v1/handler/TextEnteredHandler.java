package com.github.mishchuk7.ecomednpbot.v1.handler;

import com.github.mishchuk7.ecomednpbot.v1.client.DocumentManager;
import com.github.mishchuk7.ecomednpbot.v1.client.DocumentManagerConfig;
import com.github.mishchuk7.ecomednpbot.v1.client.InternetDocumentManagerImpl;
import com.github.mishchuk7.ecomednpbot.v1.enums.TrackingStatusColor;
import com.github.mishchuk7.ecomednpbot.v1.helper.KeyboardHelper;
import com.github.mishchuk7.ecomednpbot.v1.model.InternetDocument;
import com.github.mishchuk7.ecomednpbot.v1.model.MethodProperties;
import com.github.mishchuk7.ecomednpbot.v1.model.SearchRequest;
import com.github.mishchuk7.ecomednpbot.v1.model.UserRequest;
import com.github.mishchuk7.ecomednpbot.v1.service.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.github.mishchuk7.ecomednpbot.v1.client.InternetDocumentManager.calledMethod;
import static com.github.mishchuk7.ecomednpbot.v1.client.InternetDocumentManager.modelName;

@Slf4j
@Component
@RequiredArgsConstructor
public class TextEnteredHandler extends UserRequestHandler {

    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;
    private final DocumentManagerConfig documentManagerConfig;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isTextMessage(request.getUpdate());
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        String message;
        String userInput = dispatchRequest.getUpdate().getMessage().getText();
        String searchData = parseInputString(userInput);
        DocumentManager<InternetDocument> internetDocumentManager = new InternetDocumentManagerImpl(documentManagerConfig);
        SearchRequest searchRequest = SearchRequest.builder()
                .apiKey(documentManagerConfig.getApiKey())
                .calledMethod(calledMethod.getValue())
                .modelName(modelName.getValue())
                .methodProperties(new MethodProperties(searchData))
                .build();
        try {
            List<InternetDocument> internetDocuments = internetDocumentManager.getAllDocuments(searchRequest);
            String notFound = "Відправлень не знайдено";
            message = internetDocuments.isEmpty() ? notFound : getNumberAndStatusOfDocument(internetDocuments.get(0));
            telegramService.sendMessage(dispatchRequest.getChatId(), message, keyboardHelper.buildSearchMenu(searchData));
        } catch (IOException | InterruptedException e) {
            log.error("Exception: ", e);
        }

    }

    @Override
    public boolean isGlobal() {
        return false;
    }

    private static String parseInputString(String input) {

        String digits = extractDigits(input);
        String letters = extractLetters(input);

        return digits.isEmpty() ? letters : digits;
    }


    private static String extractDigits(String input) {
        StringBuilder digits = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isDigit(ch)) {
                digits.append(ch);
            }
        }
        return digits.toString();
    }

    private static String extractLetters(String input) {
        String s = input.replaceAll("\\p{Punct}", "");
        String[] words = s.trim().split("\\s+");
        return words.length > 0 ? words[0].replaceAll("\\P{IsCyrillic}", "") : "";
    }

    private String getNumberAndStatusOfDocument(InternetDocument document) {
        return "<b>" + document.getNumber() + "</b>" + " "
                + "<i>" + document.getTrackingStatusName() + "</i>"
                + TrackingStatusColor.getStatusColor(document.getTrackingStatusCode()).getColor();
    }
}
