package com.github.mishchuk7.ecomednpbot.v1.util;

import com.github.mishchuk7.ecomednpbot.v1.model.TrackingDocument;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class OutputHelper {

    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private OutputHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String formatOverdueParcel(TrackingDocument overdueParcel) {
        return "<b>Відправлення:</b> " + overdueParcel.getNumber()
                + "\n<b>Платне зберігання з:</b> " + overdueParcel.getDateFirstDayStorage()
                + "\n<b>Вартість зберігання</b>" + overdueParcel.getStoragePrice()
                + "\n<b>Статус доставки:</b> " + overdueParcel.getStatus() + "\n";
    }

    public static String formatDateTime(String dateTime) {
        if (dateTime == null || dateTime.isEmpty()) {
            log.error("The input date is null or empty");
            return "";
        }
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(dateTime, inputFormat);
        } catch (DateTimeParseException e) {
            log.error(e.getMessage());
            return "0001-01-01 00:00:00";
        }
        return date.format(outputFormat);
    }

    public static String formatDateTime(String dateTime, String outputFormat) {
        DateTimeFormatter output = DateTimeFormatter.ofPattern(outputFormat);
        LocalDateTime date = LocalDateTime.parse(dateTime, inputFormat);
        return date.format(output);
    }

}
