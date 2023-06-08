package com.github.mishchuk7.ecomednpbot.v1.client;

import com.github.mishchuk7.ecomednpbot.v1.model.MethodProperties;
import com.github.mishchuk7.ecomednpbot.v1.model.SearchRequest;
import com.github.mishchuk7.ecomednpbot.v1.model.TrackingDocument;
import com.github.mishchuk7.ecomednpbot.v1.util.SearchRequestUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    private static DocumentManagerConfig documentManagerConfig() {
        return new DocumentManagerConfig("baseUrl", "apiKey");
    }

    private static SearchRequest searchRequest(String documentNumber, String phone, DocumentManagerConfig documentManagerConfig) {
        return SearchRequestUtils.createSearchRequestTrackingDoc(new MethodProperties.Document(documentNumber, phone), documentManagerConfig);
    }
}