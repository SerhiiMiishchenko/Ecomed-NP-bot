package com.github.mishchuk7.ecomednpbot.v1.enums;

public enum TypeOfDocument {

    INCOMING("Incoming"),
    OUTGOING("Outgoing");

    final String value;


    TypeOfDocument(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
