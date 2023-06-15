package com.github.mishchuk7.ecomednpbot.v1.model;

import lombok.Data;

import java.util.List;

@Data
public class InternetDocumentResponse {

    private boolean success;

    private List<ResultData> data;

    private List<String> errors;
    private List<String> warnings;
    private Info info;
    private List<String> messageCodes;
    private List<String> errorCodes;
    private List<String> warningCodes;
    private List<String> infoCodes;

    public record ResultData(List<InternetDocument> result) {

    }

    public record Info(int totalCount) {

    }
}
