package com.github.mishchuk7.ecomednpbot.v1.client;

import com.github.mishchuk7.ecomednpbot.v1.enums.CargoType;
import com.github.mishchuk7.ecomednpbot.v1.enums.TypeOfDocument;
import com.github.mishchuk7.ecomednpbot.v1.model.InternetDocument;
import com.github.mishchuk7.ecomednpbot.v1.model.InternetDocumentResponse;
import com.github.mishchuk7.ecomednpbot.v1.model.SearchRequest;
import com.github.mishchuk7.ecomednpbot.v1.util.HttpRequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InternetDocumentManagerImpl implements InternetDocumentManager {

    private final DocumentManagerConfig documentManagerConfig;

    @Override
    public List<InternetDocument> getAllDocuments(SearchRequest searchRequest) throws IOException, InterruptedException {
        InternetDocumentResponse internetDocumentResponse = HttpRequestUtils.post(documentManagerConfig.getBaseUrl(), searchRequest, InternetDocumentResponse.class);
        return internetDocumentResponse.getData().stream()
                .map(InternetDocumentResponse.ResultData::getResult)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public double getTotalWeightOfParcelsAtBranch(List<InternetDocument> internetDocuments) {
        return internetDocuments.stream()
                .filter(id -> id.getTrackingStatusCode() == 7
                        && id.getTypeOfDocument().equals(TypeOfDocument.INCOMING.getValue()))
                .mapToDouble(InternetDocument::getDocumentWeight)
                .sum();
    }

    @Override
    public int getQuantityOfPallet(List<InternetDocument> internetDocuments) {
        return internetDocuments.stream()
                .filter(id -> id.getTrackingStatusCode() == 7
                        && id.getTypeOfDocument().equals(TypeOfDocument.INCOMING.getValue())
                        && id.getCargoType().equalsIgnoreCase(CargoType.PALLET.getRef()))
                .mapToInt(InternetDocument::getSeatsAmount)
                .sum();
    }

    @Override
    public int getSeatsAmount(List<InternetDocument> internetDocuments) {
        return internetDocuments.stream()
                .filter(id -> id.getTrackingStatusCode() == 7
                        && id.getTypeOfDocument().equals(TypeOfDocument.INCOMING.getValue()))
                .mapToInt(InternetDocument::getSeatsAmount)
                .sum();
    }
}
