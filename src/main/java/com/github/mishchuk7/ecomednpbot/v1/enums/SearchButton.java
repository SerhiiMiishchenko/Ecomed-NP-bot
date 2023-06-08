package com.github.mishchuk7.ecomednpbot.v1.enums;

public enum SearchButton {
    DETAILED_SEARCH("Детальна інформація"),
    SHIPPING_LIST("Перелік відправлень");

    private final String value;

    SearchButton(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
