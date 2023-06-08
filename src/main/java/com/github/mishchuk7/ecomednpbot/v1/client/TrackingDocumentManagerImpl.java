package com.github.mishchuk7.ecomednpbot.v1.client;

import com.github.mishchuk7.ecomednpbot.v1.model.SearchRequest;
import com.github.mishchuk7.ecomednpbot.v1.model.TrackingDocument;
import com.github.mishchuk7.ecomednpbot.v1.model.TrackingDocumentResponse;
import com.github.mishchuk7.ecomednpbot.v1.util.HttpRequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class TrackingDocumentManagerImpl implements TrackingDocumentManager {

    private final DocumentManagerConfig documentManagerConfig;

    @Override
    public List<TrackingDocument> getAllDocuments(SearchRequest searchRequest) throws IOException, InterruptedException {
        TrackingDocumentResponse trackingDocumentResponse = trackingDocumentResponse(searchRequest);
        if (!trackingDocumentResponse.getErrors().isEmpty()) {
            log.warn(trackingDocumentResponse.getErrors().get(0));
        }
        if (!trackingDocumentResponse.getWarnings().isEmpty()) {
            log.warn(trackingDocumentResponse.getWarnings().get(0));
        }
        if (!trackingDocumentResponse.getErrorCodes().isEmpty()) {
            log.warn(trackingDocumentResponse.getErrorCodes().get(0));
        }
        return trackingDocumentResponse(searchRequest).getData();
    }

    @Override
    public List<TrackingDocument> getProblemParcels(List<TrackingDocument> documents) {
        List<TrackingDocument> arrivedParselsList = getArrivedParselsList(documents);
        List<TrackingDocument> refusedParcelsList = getRefusedParcelsList(documents);
        if (arrivedParselsList.isEmpty() && refusedParcelsList.isEmpty()) return List.of();
        LocalDate now = LocalDate.now();
        arrivedParselsList = arrivedParselsList.stream()
                .filter(document -> LocalDate.parse(document.getDateCreated(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")).isEqual(now.minusDays(7L)))
                .filter(document -> getDate(document.getDatePayedKeeping()).isBefore(now) || getDate(document.getDatePayedKeeping()).isEqual(now))
                .toList();
        return refusedParcelsList.isEmpty() ? arrivedParselsList : Stream.concat(arrivedParselsList.stream(), refusedParcelsList.stream()).toList();
    }

    private TrackingDocumentResponse trackingDocumentResponse(SearchRequest searchRequest) throws IOException, InterruptedException {
        return HttpRequestUtils.post(documentManagerConfig.getBaseUrl(), searchRequest, TrackingDocumentResponse.class);
    }

    private List<TrackingDocument> getArrivedParselsList(List<TrackingDocument> documents) {
        return documents.stream().filter(document -> document.getStatusCode() == 7 || document.getStatusCode() == 8).toList();
    }

    private List<TrackingDocument> getRefusedParcelsList(List<TrackingDocument> documents) {
        return documents.stream().filter(document -> document.getStatusCode() == 102
                        || document.getStatusCode() == 103
                        || document.getStatusCode() == 105)
                .toList();
    }

    private LocalDate getDate(String date) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDate.parse(date, inputFormat);
    }
}
