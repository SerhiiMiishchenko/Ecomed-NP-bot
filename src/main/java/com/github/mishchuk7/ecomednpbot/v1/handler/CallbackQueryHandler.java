package com.github.mishchuk7.ecomednpbot.v1.handler;

import com.github.mishchuk7.ecomednpbot.v1.client.*;
import com.github.mishchuk7.ecomednpbot.v1.enums.SearchButton;
import com.github.mishchuk7.ecomednpbot.v1.enums.TrackingStatusColor;
import com.github.mishchuk7.ecomednpbot.v1.model.*;
import com.github.mishchuk7.ecomednpbot.v1.service.TelegramService;
import com.github.mishchuk7.ecomednpbot.v1.util.OutputHelper;
import com.github.mishchuk7.ecomednpbot.v1.util.SearchRequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.mishchuk7.ecomednpbot.v1.enums.SearchButton.DETAILED_SEARCH;


@Slf4j
@Component
@RequiredArgsConstructor
public class CallbackQueryHandler extends UserRequestHandler {

    private final TelegramService telegramService;
    private final DocumentManagerConfig documentManagerConfig;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCallbackQuery(request.getUpdate());
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        String message;
        String[] callbackData = dispatchRequest.getUpdate().getCallbackQuery().getData().split(":");
        String searchData = callbackData[0];
        String buttonText = callbackData[1];

        try {
            DocumentManager<InternetDocument> internetDocumentManager = DocumentManagerFactory.getInternetDocumentManager(documentManagerConfig);
            DocumentManager<TrackingDocument> trackingDocumentManager = DocumentManagerFactory.getTrackingDocumentManager(documentManagerConfig);
            SearchRequest searchRequestInternetDoc = SearchRequestUtils.createSearchRequestInternetDoc(searchData, documentManagerConfig);
            List<InternetDocument> internetDocuments = internetDocumentManager.getAllDocuments(searchRequestInternetDoc);
            String notFound = "Відправлень не знайдено";
            if (internetDocuments.isEmpty()) {
                message = notFound;
            } else if (SearchButton.valueOf(buttonText) == DETAILED_SEARCH) {
                InternetDocument internetDocument = internetDocuments.get(0);
                MethodProperties.Document document = new MethodProperties.Document(internetDocument.getNumber(), internetDocument.getPhoneSender());
                SearchRequest searchRequestTrackingDoc = SearchRequestUtils.createSearchRequestTrackingDoc(document, documentManagerConfig);
                TrackingDocument trackingDocument = trackingDocumentManager.getAllDocuments(searchRequestTrackingDoc).get(0);
                message = internetDocument + trackingDocument.toString();
            } else {
                message = String.format("%-35s %4s %-34s", "<b>Номер</b>","<b>Ст </b>", "<b> Дата ств.</b>") + "\n" +
                        internetDocuments.stream()
                                .limit(10)
                                .map(i -> i.getNumber() + " | "
                                        + TrackingStatusColor.getStatusColor(i.getTrackingStatusCode()).getColor()
                                        + " | " + OutputHelper.formatDateTime(i.getDateTime(), "dd.MM.yyyy"))
                                .collect(Collectors.joining("\n"));
            }

            telegramService.sendMessage(dispatchRequest.getChatId(), message);
        } catch (IOException | InterruptedException e) {
            log.error("Exception: ", e);
        }

    }

    @Override
    public boolean isGlobal() {
        return false;
    }

}
