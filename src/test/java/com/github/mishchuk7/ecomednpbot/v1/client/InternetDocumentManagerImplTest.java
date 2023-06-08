package com.github.mishchuk7.ecomednpbot.v1.client;

import com.github.mishchuk7.ecomednpbot.v1.model.InternetDocument;
import com.github.mishchuk7.ecomednpbot.v1.model.SearchRequest;
import com.github.mishchuk7.ecomednpbot.v1.util.SearchRequestUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InternetDocumentManagerImplTest {

    private final DocumentManagerConfig documentManagerConfig = new DocumentManagerConfig("baseUrl", "apiKey");
    private final SearchRequest searchRequest = SearchRequestUtils.createSearchRequestInternetDoc("basePhoneNumber", documentManagerConfig);



    @Test
    @SneakyThrows
    void getAllDocumentsList() {
        List<InternetDocument> internetDocuments = new InternetDocumentManagerImpl(documentManagerConfig).getAllDocuments(searchRequest);

        assertNotEquals(internetDocuments.size(), 0);
    }

    @Test
    @SneakyThrows
    void commonWeightEqualZeroIfAllParcelsAreReceived() {
        List<InternetDocument> receivedParcels = new InternetDocumentManagerImpl(documentManagerConfig).getAllDocuments(searchRequest).stream()
                .filter(document -> document.getTrackingStatusCode() == 9)
                .toList();

        assertEquals(new InternetDocumentManagerImpl(documentManagerConfig).getTotalWeightOfParcelsAtBranch(receivedParcels), 0.0);
    }

    @Test
    @SneakyThrows
    void quantityOfPalletEqualZeroIfAllParcelsAreReceived() {
        List<InternetDocument> receivedParcels = new InternetDocumentManagerImpl(documentManagerConfig).getAllDocuments(searchRequest).stream()
                .filter(document -> document.getTrackingStatusCode() == 9)
                .toList();

        assertEquals(new InternetDocumentManagerImpl(documentManagerConfig).getQuantityOfPallet(receivedParcels), 0.0);
    }

    @Test
    @SneakyThrows
    void seatsAmountEqualZeroIfAllParcelsAreReceived() {
        List<InternetDocument> receivedParcels = new InternetDocumentManagerImpl(documentManagerConfig).getAllDocuments(searchRequest).stream()
                .filter(document -> document.getTrackingStatusCode() == 9)
                .toList();

        assertEquals(new InternetDocumentManagerImpl(documentManagerConfig).getSeatsAmount(receivedParcels), 0.0);
    }

}