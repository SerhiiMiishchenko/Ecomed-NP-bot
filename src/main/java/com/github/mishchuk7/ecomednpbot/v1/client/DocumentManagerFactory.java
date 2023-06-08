package com.github.mishchuk7.ecomednpbot.v1.client;

public class DocumentManagerFactory {

    public static InternetDocumentManager getInternetDocumentManager(DocumentManagerConfig documentManagerConfig) {
        return new InternetDocumentManagerImpl(documentManagerConfig);
    }

    public static TrackingDocumentManager getTrackingDocumentManager(DocumentManagerConfig documentManagerConfig) {
        return new TrackingDocumentManagerImpl(documentManagerConfig);
    }

}
