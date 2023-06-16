package com.github.mishchuk7.ecomednpbot.v1.enums;

public enum TypeOfDocument {
    INCOMING("Incoming"), OUTGOING("Outgoing");

    final String description;

    TypeOfDocument(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
