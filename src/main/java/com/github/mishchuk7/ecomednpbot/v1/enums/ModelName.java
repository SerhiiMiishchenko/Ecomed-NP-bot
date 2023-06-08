package com.github.mishchuk7.ecomednpbot.v1.enums;

public enum ModelName {

    INTERNET_DOCUMENT("InternetDocument"),
    TRACKING_DOCUMENT("TrackingDocument");

    final String value;

    ModelName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
