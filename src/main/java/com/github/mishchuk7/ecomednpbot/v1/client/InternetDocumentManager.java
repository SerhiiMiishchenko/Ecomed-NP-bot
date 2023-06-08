package com.github.mishchuk7.ecomednpbot.v1.client;

import com.github.mishchuk7.ecomednpbot.v1.enums.CalledMethod;
import com.github.mishchuk7.ecomednpbot.v1.enums.ModelName;
import com.github.mishchuk7.ecomednpbot.v1.model.InternetDocument;

import java.util.List;

public interface InternetDocumentManager extends DocumentManager<InternetDocument> {

    ModelName modelName = ModelName.INTERNET_DOCUMENT;
    CalledMethod calledMethod = CalledMethod.FIND_DOCUMENT_BY_DATA;

    double getTotalWeightOfParcelsAtBranch(List<InternetDocument> internetDocuments);

    int getQuantityOfPallet(List<InternetDocument> internetDocuments);

    int getSeatsAmount(List<InternetDocument> internetDocuments);

}
