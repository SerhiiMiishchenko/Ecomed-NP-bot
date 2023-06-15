package com.github.mishchuk7.ecomednpbot.v1.handler;

import com.github.mishchuk7.ecomednpbot.v1.client.DocumentManager;
import com.github.mishchuk7.ecomednpbot.v1.client.DocumentManagerConfig;
import com.github.mishchuk7.ecomednpbot.v1.client.DocumentManagerFactory;
import com.github.mishchuk7.ecomednpbot.v1.enums.SearchButton;
import com.github.mishchuk7.ecomednpbot.v1.enums.TrackingStatusColor;
import com.github.mishchuk7.ecomednpbot.v1.model.InternetDocument;
import com.github.mishchuk7.ecomednpbot.v1.model.MethodProperties;
import com.github.mishchuk7.ecomednpbot.v1.model.TrackingDocument;
import com.github.mishchuk7.ecomednpbot.v1.model.UserRequest;
import com.github.mishchuk7.ecomednpbot.v1.service.TelegramService;
import com.github.mishchuk7.ecomednpbot.v1.util.OutputHelper;
import com.github.mishchuk7.ecomednpbot.v1.util.SearchRequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


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
        try {
            String message = processDispatchRequest(dispatchRequest);
            telegramService.sendMessage(dispatchRequest.getChatId(), message);
        } catch (IOException | InterruptedException e) {
            log.error("Exception: ", e);
        }
    }

    @Override
    public boolean isGlobal() {
        return false;
    }

    private String processDispatchRequest(UserRequest dispatchRequest) throws IOException, InterruptedException {
        String[] callbackData = dispatchRequest.getUpdate().getCallbackQuery().getData().split(":");
        String searchData = callbackData[0];
        String buttonText = callbackData[1];

        DocumentManager<InternetDocument> internetDocumentManager = DocumentManagerFactory.getInternetDocumentManager(documentManagerConfig);
        DocumentManager<TrackingDocument> trackingDocumentManager = DocumentManagerFactory.getTrackingDocumentManager(documentManagerConfig);

        List<InternetDocument> internetDocuments = internetDocumentManager.getAllDocuments(SearchRequestUtils.createSearchRequestInternetDoc(searchData, documentManagerConfig));

        if (internetDocuments.isEmpty()) {
            return "Відправлень не знайдено";
        }

        if (SearchButton.valueOf(buttonText) == SearchButton.DETAILED_SEARCH) {
            InternetDocument internetDocument = internetDocuments.get(0);
            MethodProperties.Document document = new MethodProperties.Document(internetDocument.getNumber(), internetDocument.getPhoneSender());
            List<TrackingDocument> trackingDocuments = trackingDocumentManager.getAllDocuments(SearchRequestUtils.createSearchRequestTrackingDoc(document, documentManagerConfig));
            if (trackingDocuments.isEmpty()) {
                return internetDocument.toString();
            }
            TrackingDocument trackingDocument = trackingDocuments.get(0);
            return internetDocument.toString() + trackingDocument.toString();
        }

        String header = String.format("%-35s %4s %-34s", "<b>Номер</b>", "<b>Ст </b>", "<b> Дата ств.</b>");
        String internetDocumentLines = internetDocuments.stream()
                .limit(10)
                .map(i -> i.getNumber() + " | "
                        + TrackingStatusColor.getStatusColor(i.getTrackingStatusCode()).getColor()
                        + " | " + OutputHelper.formatDateTime(i.getDateTime(), "dd.MM.yyyy"))
                .collect(Collectors.joining("\n"));
        return header + "\n" + internetDocumentLines;
    }

}
