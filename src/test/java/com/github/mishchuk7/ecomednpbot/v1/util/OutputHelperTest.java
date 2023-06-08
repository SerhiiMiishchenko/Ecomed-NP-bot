package com.github.mishchuk7.ecomednpbot.v1.util;

import com.github.mishchuk7.ecomednpbot.v1.enums.TrackingStatusColor;
import com.github.mishchuk7.ecomednpbot.v1.model.TrackingDocument;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutputHelperTest {

    @Test
    void testFormatOverdueParcel_ValidParcel_ReturnsFormattedString() {
        TrackingDocument overdueParcel = new TrackingDocument();
        overdueParcel.setNumber("ABC123");
        overdueParcel.setDateFirstDayStorage("2023-01-01");
        overdueParcel.setStatus("Delivered");
        String formattedParcel = OutputHelper.formatOverdueParcel(overdueParcel);
        String expectedFormattedParcel = """
                <b>Відправлення:</b> ABC123 грн
                <b>Платне зберігання з:</b> 2023-01-01
                <b>Статус доставки:</b> Delivered
                """;
        assertEquals(expectedFormattedParcel, formattedParcel);
    }

    @Test
    void testFormatDateTime_ValidInput_ReturnsFormattedDateTime() {
        String inputDateTime = "2023-05-17 10:15:30";
        String expectedFormattedDateTime = "17.05.2023 10:15:30";
        String formattedDateTime = OutputHelper.formatDateTime(inputDateTime);

        assertEquals(expectedFormattedDateTime, formattedDateTime);
    }

    @Test
    void testFormatDateTime_InvalidInput_ReturnsEmptyString() {
        String invalidDateTime = "2023-99-99 99:99:99";
        String expectedEmptyString = "";
        String formattedDateTime = OutputHelper.formatDateTime(invalidDateTime);

        assertEquals(expectedEmptyString, formattedDateTime);
    }

    @Test
    void testFormatDateTime_NullInput_ReturnsEmptyString() {
        String expectedEmptyString = "";
        String formattedDateTime = OutputHelper.formatDateTime(null);

        assertEquals(expectedEmptyString, formattedDateTime);
    }

    @Test
    void testFormatDateTimeWithTwoArguments_ValidInput_ReturnsFormattedDateTime() {
        String inputDateTime = "2023-05-17 10:15:30";
        String expectedFormattedDateTime = "17.05.2023";
        String formattedDateTime = OutputHelper.formatDateTime(inputDateTime, "dd.MM.yyyy");

        assertEquals(expectedFormattedDateTime, formattedDateTime);
    }

    @Test
    void testGetStatusColor_ValidStatusCode_ReturnsCorrectColor() {
        int validStatusCode = 1;
        TrackingStatusColor expectedColor = TrackingStatusColor.GREY;
        TrackingStatusColor actualColor = TrackingStatusColor.getStatusColor(validStatusCode);

        assertEquals(expectedColor, actualColor);
    }

}