package com.github.mishchuk7.ecomednpbot.v1.enums;

public enum PaymentMethod {
    NON_CASH("NonCash", "Безготівка"),
    CASH("Cash", "Готівка");

    private final String ref;
    private final String description;

    PaymentMethod(String ref, String description) {
        this.ref = ref;
        this.description = description;
    }

    public String getRef() {
        return ref;
    }

    public String getDescription() {
        return description;
    }

    public static String descriptionOf(String method) {
        for (PaymentMethod payment : values()) {
            if (payment.getRef().equalsIgnoreCase(method)) return payment.getDescription();
        }
        return "";
    }
}
