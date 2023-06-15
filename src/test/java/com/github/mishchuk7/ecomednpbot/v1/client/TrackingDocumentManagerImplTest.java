package com.github.mishchuk7.ecomednpbot.v1.client;

import com.github.mishchuk7.ecomednpbot.v1.model.MethodProperties;
import com.github.mishchuk7.ecomednpbot.v1.model.SearchRequest;
import com.github.mishchuk7.ecomednpbot.v1.model.TrackingDocument;
import com.github.mishchuk7.ecomednpbot.v1.util.SearchRequestUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TrackingDocumentManagerImplTest {


    @Test
    @SneakyThrows
    void receiveAllDocumentsList() {
        DocumentManagerConfig documentManagerConfig = documentManagerConfig();
        SearchRequest searchRequest = searchRequest("20XXXXXXXXX0", "380XXXXXXX", documentManagerConfig);

        List<TrackingDocument> trackingDocuments = new TrackingDocumentManagerImpl(documentManagerConfig).getAllDocuments(searchRequest);

        assertNotEquals(trackingDocuments.size(), 0);
    }

    @Test
    @SneakyThrows
    void receiveEmptyDocumentListIfDocumentNumberIncorrect() {
        DocumentManagerConfig documentManagerConfig = documentManagerConfig();
        SearchRequest searchRequest = searchRequest("20XXXXXXXXX0", "380XXXXXXX", documentManagerConfig);

        List<TrackingDocument> trackingDocuments = new TrackingDocumentManagerImpl(documentManagerConfig).getAllDocuments(searchRequest);

        assertEquals(trackingDocuments.size(), 0);
    }

    @Test
    @SneakyThrows
    void problemParcelsListIsEmptyIfParcelsAreNotProblem() {
        DocumentManagerConfig documentManagerConfig = documentManagerConfig();
        SearchRequest searchRequest = searchRequest("380XXXXXXX", "380XXXXXXX", documentManagerConfig);

        List<TrackingDocument> trackingDocuments = new TrackingDocumentManagerImpl(documentManagerConfig).getAllDocuments(searchRequest);
        List<TrackingDocument> overdueParcels = new TrackingDocumentManagerImpl(documentManagerConfig).getProblemParcels(trackingDocuments);

        assertEquals(overdueParcels.size(), 0);
    }

    @Test
    @SneakyThrows
    void problemParcelsListIsNotEmptyWhenParcelsAreOverdue() {
        List<TrackingDocument> trackingDocuments = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            TrackingDocument document = new TrackingDocument();
            document.setDateCreated(LocalDateTime.now().minusDays(8L).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            document.setDatePayedKeeping(LocalDateTime.now().minusDays(1L).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            document.setStatusCode(7);
            trackingDocuments.add(document);
        }
        List<TrackingDocument> parcels = new TrackingDocumentManagerImpl(documentManagerConfig()).getProblemParcels(trackingDocuments);

        assertEquals(parcels.size(), 10_000);
    }

    private static DocumentManagerConfig documentManagerConfig() {
        return new DocumentManagerConfig("baseUrl", "apiKey");
    }

    private static SearchRequest searchRequest(String documentNumber, String phone, DocumentManagerConfig documentManagerConfig) {
        return SearchRequestUtils.createSearchRequestTrackingDoc(new MethodProperties.Document(documentNumber, phone), documentManagerConfig);
    }
}