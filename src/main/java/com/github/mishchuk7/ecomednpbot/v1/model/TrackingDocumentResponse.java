package com.github.mishchuk7.ecomednpbot.v1.model;

import lombok.Data;

import java.util.List;

@Data
public class TrackingDocumentResponse {

    private boolean success;
    private List<TrackingDocument> data;
    private List<String> errors;
    private List<String> warnings;
    private List<String> info;
    private List<String> messageCodes;
    private List<String> errorCodes;
    private List<String> warningCodes;
    private List<String> infoCodes;

}

