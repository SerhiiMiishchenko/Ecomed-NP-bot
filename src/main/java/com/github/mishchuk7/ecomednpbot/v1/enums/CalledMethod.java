package com.github.mishchuk7.ecomednpbot.v1.enums;

public enum CalledMethod {

    FIND_DOCUMENT_BY_DATA("findDocumentByData"),
    GET_STATUS_DOCUMENTS("getStatusDocuments");

    final String value;

    CalledMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
