package com.github.mishchuk7.ecomednpbot.v1.client;

import com.github.mishchuk7.ecomednpbot.v1.model.InternetDocument;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

class InternetDocumentManagerImplTest {


    @Test
    @SneakyThrows
    void allDocumentsListIsNotEmpty() {
        List<InternetDocument> internetDocuments = Collections.singletonList(mock(InternetDocument.class));

        assertNotEquals(0, internetDocuments.size());
    }

    @Test
    @SneakyThrows
    void commonWeightEqualZeroIfParcelWeightIsZero() {
        List<InternetDocument> receivedParcels = Collections.singletonList(mock(InternetDocument.class));

        assertEquals(0.0, mock(InternetDocumentManagerImpl.class).getTotalWeightOfParcelsAtBranch(receivedParcels,"City", 1));
    }

}