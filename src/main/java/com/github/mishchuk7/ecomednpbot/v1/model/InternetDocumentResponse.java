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

    public static class ResultData {

        private List<InternetDocument> result;

        public List<InternetDocument> getResult() {
            return result;
        }
    }

    public static class Info {
        private int totalCount;

        public int getTotalCount() {
            return totalCount;
        }

    }
}
