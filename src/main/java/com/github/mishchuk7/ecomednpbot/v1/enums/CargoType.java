package com.github.mishchuk7.ecomednpbot.v1.enums;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum CargoType {
    PARCEL("Parcel", "Посилка"),
    CARGO("Cargo", "Вантаж"),
    DOCUMENTS("Documents", "Документи"),
    TIRES_WHEELS("TiresWheels", "Шини-диски"),
    PALLET("Pallet", "Палет");

    final String ref;
    final String description;

    CargoType(String ref, String description) {
        this.ref = ref;
        this.description = description;
    }

    public String getRef() {
        return ref;
    }

    public String getDescription() {
        return description;
    }

    public static String descriptionOf(String type) {
        for (CargoType cargoType : values()) {
            if (type.equalsIgnoreCase(cargoType.getRef())) return cargoType.getDescription();
        }
        log.error("Unknown Cargo type");
        return "";
    }

}
