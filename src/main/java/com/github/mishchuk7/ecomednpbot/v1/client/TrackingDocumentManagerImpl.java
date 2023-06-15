package com.github.mishchuk7.ecomednpbot.v1.client;

import com.github.mishchuk7.ecomednpbot.v1.model.SearchRequest;
import com.github.mishchuk7.ecomednpbot.v1.model.TrackingDocument;
import com.github.mishchuk7.ecomednpbot.v1.model.TrackingDocumentResponse;
import com.github.mishchuk7.ecomednpbot.v1.util.HttpRequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class TrackingDocumentManagerImpl implements TrackingDocumentManager {

    private final DocumentManagerConfig documentManagerConfig;

    @Override
    public List<TrackingDocument> getAllDocuments(SearchRequest searchRequest) throws IOException, InterruptedException {
        TrackingDocumentResponse trackingDocumentResponse = trackingDocumentResponse(searchRequest);
        logResponseError(trackingDocumentResponse);
        return trackingDocumentResponse(searchRequest).getData();
    }

    @Override
    public List<TrackingDocument> getProblemParcels(List<TrackingDocument> documents) {
        List<TrackingDocument> overdueParcels = documents.stream()
                .filter(document -> !document.getStorageAmount().isEmpty())
                .toList();
        List<TrackingDocument> refusedParcelsList = getRefusedParcelsList(documents);
        return Stream.concat(overdueParcels.stream(), refusedParcelsList.stream()).toList();
    }

    private TrackingDocumentResponse trackingDocumentResponse(SearchRequest searchRequest) throws IOException, InterruptedException {
        return HttpRequestUtils.post(documentManagerConfig.getBaseUrl(), searchRequest, TrackingDocumentResponse.class);
    }

    private List<TrackingDocument> getRefusedParcelsList(List<TrackingDocument> documents) {
        return documents.stream().filter(document -> document.getStatusCode() == 102
                        || document.getStatusCode() == 103
                        || document.getStatusCode() == 105)
                .toList();
    }

    private static void logResponseError(TrackingDocumentResponse trackingDocumentResponse) {
        if (!trackingDocumentResponse.getErrors().isEmpty()) {
            log.warn(trackingDocumentResponse.getErrors().get(0));
        }
        if (!trackingDocumentResponse.getWarnings().isEmpty()) {
            log.warn(trackingDocumentResponse.getWarnings().get(0));
        }
        if (!trackingDocumentResponse.getErrorCodes().isEmpty()) {
            log.warn(trackingDocumentResponse.getErrorCodes().get(0));
        }
    }
}
