package com.github.mishchuk7.ecomednpbot.v1.handler;

import com.github.mishchuk7.ecomednpbot.v1.client.DocumentManager;
import com.github.mishchuk7.ecomednpbot.v1.client.DocumentManagerConfig;
import com.github.mishchuk7.ecomednpbot.v1.client.InternetDocumentManagerImpl;
import com.github.mishchuk7.ecomednpbot.v1.enums.TrackingStatusColor;
import com.github.mishchuk7.ecomednpbot.v1.helper.KeyboardHelper;
import com.github.mishchuk7.ecomednpbot.v1.model.InternetDocument;
import com.github.mishchuk7.ecomednpbot.v1.model.SearchRequest;
import com.github.mishchuk7.ecomednpbot.v1.model.UserRequest;
import com.github.mishchuk7.ecomednpbot.v1.service.TelegramService;
import com.github.mishchuk7.ecomednpbot.v1.util.SearchRequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

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
        String notFound = "Відправлень не знайдено";
        String message;
        String userInput = dispatchRequest.getUpdate().getMessage().getText();
        String searchData = parseInputString(userInput);
        if (searchData.isEmpty()) {
            telegramService.sendMessage(dispatchRequest.getChatId(), notFound);
            return;
        }
        DocumentManager<InternetDocument> internetDocumentManager = new InternetDocumentManagerImpl(documentManagerConfig);
        SearchRequest searchRequest = SearchRequestUtils.createSearchRequestInternetDoc(searchData, documentManagerConfig);
        try {
            List<InternetDocument> internetDocuments = internetDocumentManager.getAllDocuments(searchRequest);
            message = internetDocuments.isEmpty() ? notFound : getNumberAndStatusOfDocument(internetDocuments.get(0));
            if (message.equals(notFound)) {
                telegramService.sendMessage(dispatchRequest.getChatId(), message);
            } else {
                telegramService.sendMessage(dispatchRequest.getChatId(), message, keyboardHelper.buildSearchMenu(searchData));
            }
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
        if (input.matches("[a-zA-Z]")) return "";
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
