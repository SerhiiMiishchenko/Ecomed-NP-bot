package com.github.mishchuk7.ecomednpbot.v1.util;

import com.github.mishchuk7.ecomednpbot.v1.client.DocumentManagerConfig;
import com.github.mishchuk7.ecomednpbot.v1.client.InternetDocumentManager;
import com.github.mishchuk7.ecomednpbot.v1.client.TrackingDocumentManager;
import com.github.mishchuk7.ecomednpbot.v1.model.MethodProperties;
import com.github.mishchuk7.ecomednpbot.v1.model.SearchRequest;

import java.util.List;

public class SearchRequestUtils {

    private SearchRequestUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static SearchRequest createSearchRequestInternetDoc(String searchData, DocumentManagerConfig documentManagerConfig) {
        return SearchRequest.builder()
                .apiKey(documentManagerConfig.getApiKey())
                .modelName(InternetDocumentManager.modelName.getValue())
                .calledMethod(InternetDocumentManager.calledMethod.getValue())
                .methodProperties(new MethodProperties(searchData))
                .build();
    }

    public static SearchRequest createSearchRequestTrackingDoc(MethodProperties.Document document, DocumentManagerConfig documentManagerConfig) {
        return SearchRequest.builder()
                .apiKey(documentManagerConfig.getApiKey())
                .modelName(TrackingDocumentManager.modelName.getValue())
                .calledMethod(TrackingDocumentManager.calledMethod.getValue())
                .methodProperties(new MethodProperties(List.of(document)))
                .build();
    }

}
