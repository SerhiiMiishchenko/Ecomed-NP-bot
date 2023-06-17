package com.github.mishchuk7.ecomednpbot.v1.handler;

import com.github.mishchuk7.ecomednpbot.v1.client.*;
import com.github.mishchuk7.ecomednpbot.v1.exception.EcomedNpTelegramBotException;
import com.github.mishchuk7.ecomednpbot.v1.model.*;
import com.github.mishchuk7.ecomednpbot.v1.service.TelegramService;
import com.github.mishchuk7.ecomednpbot.v1.util.OutputHelper;
import com.github.mishchuk7.ecomednpbot.v1.util.SearchRequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class ProblemParcelsHandler extends UserRequestHandler {

    private static final String PROBLEM = "/problem";

    @Value("${BASE_PHONE_NUMBER}")
    private String baseNumber;

    private final TelegramService telegramService;
    private final DocumentManagerConfig documentManagerConfig;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(), PROBLEM);
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        try {
            DocumentManager<InternetDocument> internetDocumentManager = DocumentManagerFactory.getInternetDocumentManager(documentManagerConfig);
            TrackingDocumentManager trackingDocumentManager = DocumentManagerFactory.getTrackingDocumentManager(documentManagerConfig);
            SearchRequest searchRequestInternetDoc = SearchRequestUtils.createSearchRequestInternetDoc(baseNumber, documentManagerConfig);

            List<InternetDocument> internetDocuments = internetDocumentManager.getAllDocuments(searchRequestInternetDoc);
            List<TrackingDocument> problemParcels = trackingDocumentManager.getProblemParcels(receiveOverdueParcels(internetDocuments));

            String message = "Проблемних відправлень не знайдено";
            StringBuilder sb = new StringBuilder();
            if (problemParcels.isEmpty()) {
                telegramService.sendMessage(dispatchRequest.getChatId(), message);
            } else {
                problemParcels.forEach(parcel -> sb.append(OutputHelper.formatOverdueParcel(parcel)));
                telegramService.sendMessage(dispatchRequest.getChatId(), sb.toString());
            }
        } catch (IOException | InterruptedException e) {
            log.warn("Interrupted!", e);
            Thread.currentThread().interrupt();
        }
    }


    @Override
    public boolean isGlobal() {
        return false;
    }

    private List<TrackingDocument> receiveOverdueParcels(List<InternetDocument> internetDocuments) {
        return internetDocuments.stream()
                .filter(document -> !document.getFirstDayStorage().isEmpty())
                .filter(document -> isDateBeforeOrEqualToCurrentDate(document.getFirstDayStorage()))
                .map(document -> new MethodProperties.Document(document.getNumber(), document.getPhoneSender()))
                .map(document -> SearchRequestUtils.createSearchRequestTrackingDoc(document, documentManagerConfig))
                .map(searchRequest -> {
                    try {
                        return new TrackingDocumentManagerImpl(documentManagerConfig).getAllDocuments(searchRequest);
                    } catch (IOException | InterruptedException e) {
                        log.warn("Interrupted!", e);
                        Thread.currentThread().interrupt();
                        throw new EcomedNpTelegramBotException("Search request create error" ,e);
                    }
                })
                .flatMap(Collection::stream)
                .toList();
    }

    private static boolean isDateBeforeOrEqualToCurrentDate(String dateString) {
        LocalDateTime currentDate = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime providedDate = LocalDateTime.parse(dateString, formatter);

        return providedDate.isEqual(currentDate) || providedDate.isBefore(currentDate);
    }

}
