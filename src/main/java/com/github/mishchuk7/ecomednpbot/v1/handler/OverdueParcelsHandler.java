package com.github.mishchuk7.ecomednpbot.v1.handler;

import com.github.mishchuk7.ecomednpbot.v1.client.DocumentManager;
import com.github.mishchuk7.ecomednpbot.v1.client.DocumentManagerConfig;
import com.github.mishchuk7.ecomednpbot.v1.client.DocumentManagerFactory;
import com.github.mishchuk7.ecomednpbot.v1.client.TrackingDocumentManager;
import com.github.mishchuk7.ecomednpbot.v1.model.*;
import com.github.mishchuk7.ecomednpbot.v1.service.TelegramService;
import com.github.mishchuk7.ecomednpbot.v1.util.OutputHelper;
import com.github.mishchuk7.ecomednpbot.v1.util.SearchRequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class OverdueParcelsHandler extends UserRequestHandler {

    private static final String command = "/overdue";

    @Value("${BASE_PHONE_NUMBER}")
    private static String baseNumber;

    private final TelegramService telegramService;
    private final DocumentManagerConfig documentManagerConfig;

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        try {
            DocumentManager<InternetDocument> internetDocumentManager = DocumentManagerFactory.getInternetDocumentManager(documentManagerConfig);
            TrackingDocumentManager trackingDocumentManager = DocumentManagerFactory.getTrackingDocumentManager(documentManagerConfig);
            SearchRequest searchRequestInternetDoc = SearchRequestUtils.createSearchRequestInternetDoc(baseNumber, documentManagerConfig);
            List<InternetDocument> internetDocuments = internetDocumentManager.getAllDocuments(searchRequestInternetDoc);
            List<MethodProperties.Document> documents = getMethodPropertiesDocuments(internetDocuments);
            List<List<TrackingDocument>> trackingDocuments = new ArrayList<>();
            SearchRequest searchRequestTrackingDoc;
            for (MethodProperties.Document document : documents) {
                searchRequestTrackingDoc = SearchRequestUtils.createSearchRequestTrackingDoc(document, documentManagerConfig);
                trackingDocuments.add(trackingDocumentManager.getAllDocuments(searchRequestTrackingDoc));
            }

            List<TrackingDocument> overdueParcelsList = trackingDocumentManager.getProblemParcels(trackingDocuments.stream()
                    .flatMap(List::stream)
                    .toList());

            String message = "Проблемних відправлень не знайдено";
            StringBuilder sb = new StringBuilder();
            if (overdueParcelsList.isEmpty()) {
                telegramService.sendMessage(dispatchRequest.getChatId(), message);
            } else {
                overdueParcelsList.forEach(parcel -> sb.append(OutputHelper.formatOverdueParcel(parcel)));
                telegramService.sendMessage(dispatchRequest.getChatId(), sb.toString());
            }
        } catch (IOException | InterruptedException e) {
            log.error("Exception: ", e);
        }
    }


    @Override
    public boolean isGlobal() {
        return false;
    }

    private static List<MethodProperties.Document> getMethodPropertiesDocuments(List<InternetDocument> internetDocuments) {
        List<InternetDocument> internetDocumentList = getArrivedParselsList(internetDocuments);
        if (internetDocumentList.isEmpty()) return List.of();
        List<MethodProperties.Document> methodPropertiesList = new ArrayList<>();
        for (InternetDocument internetDocument : internetDocuments) {
            methodPropertiesList.add(new MethodProperties.Document(internetDocument.getNumber(), internetDocument.getPhoneSender()));
        }
        return methodPropertiesList;
    }

    private static List<InternetDocument> getArrivedParselsList(List<InternetDocument> documents) {
        LocalDate now = LocalDate.now();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return documents.stream()
                .filter(document -> !document.getFirstDayStorage().equalsIgnoreCase("0001-01-01 00:00:00"))
                .filter(document -> now.isEqual(LocalDate.parse(document.getFirstDayStorage(), DateTimeFormatter.ofPattern(pattern)))
                        || now.isAfter(LocalDate.parse(document.getFirstDayStorage(), DateTimeFormatter.ofPattern(pattern))))
                .toList();
    }

}
