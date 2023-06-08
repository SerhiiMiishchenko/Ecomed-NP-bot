package com.github.mishchuk7.ecomednpbot.v1.client;

import com.github.mishchuk7.ecomednpbot.v1.enums.CalledMethod;
import com.github.mishchuk7.ecomednpbot.v1.enums.ModelName;
import com.github.mishchuk7.ecomednpbot.v1.model.TrackingDocument;

import java.util.List;

public interface TrackingDocumentManager extends DocumentManager<TrackingDocument> {

    ModelName modelName = ModelName.TRACKING_DOCUMENT;
    CalledMethod calledMethod = CalledMethod.GET_STATUS_DOCUMENTS;

    List<TrackingDocument> getProblemParcels(List<TrackingDocument> documents);
}
