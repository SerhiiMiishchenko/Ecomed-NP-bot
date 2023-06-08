package com.github.mishchuk7.ecomednpbot.v1.enums;

public enum TrackingStatusColor {

    BLUE("\uD83D\uDD35"),
    GREEN("\uD83D\uDFE2"),
    ORANGE("\uD83D\uDFE0"),
    GREY("⚫"),
    RED("\uD83D\uDD34");

    final String color;

    TrackingStatusColor(String color) {
        this.color = color;
    }

    public static TrackingStatusColor getStatusColor(int validStatusCode) {
        TrackingStatusCode trackingStatusCode = TrackingStatusCode.SENDER_CREATED;
        for (TrackingStatusCode statusCode : TrackingStatusCode.values()) {
            if (statusCode.getId() == validStatusCode) {
                trackingStatusCode = statusCode;
            }
        }
        return trackingStatusCode.getColor();
    }

    public String getColor() {
        return color;
    }
}
