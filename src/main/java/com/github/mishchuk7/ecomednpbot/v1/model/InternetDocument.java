package com.github.mishchuk7.ecomednpbot.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.mishchuk7.ecomednpbot.v1.enums.CargoType;
import com.github.mishchuk7.ecomednpbot.v1.enums.TrackingStatusColor;
import lombok.Data;
import com.github.mishchuk7.ecomednpbot.v1.util.OutputHelper;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InternetDocument {

    @JsonProperty("MoneyTransferPayerType")
    private String moneyTransferPayerType;

    @JsonProperty("MoneyTransferNumber")
    private String moneyTransferNumber;

    @JsonProperty("ClientBarcodes")
    private List<Object> clientBarcodes;

    @JsonProperty("DateTime")
    private String dateTime;

    @JsonProperty("CounterpartyRecipientDescription")
    private String counterpartyRecipientDescription;

    @JsonProperty("MoneyTransferCreationDate")
    private String moneyTransferCreationDate;

    @JsonProperty("DocumentWeight")
    private Double documentWeight;

    @JsonProperty("AfterpaymentOnGoodsCost")
    private Double afterpaymentOnGoodsCost;

    @JsonProperty("ArrivalDateTime")
    private String arrivalDateTime;

    @JsonProperty("CityRecipient")
    private String cityRecipient;

    @JsonProperty("LoyaltyCardRecipient")
    private String loyaltyCardRecipient;

    @JsonProperty("LoyaltyCardSender")
    private String loyaltyCardSender;

    @JsonProperty("DeletionMark")
    private Boolean deletionMark;

    @JsonProperty("RecipientFullName")
    private String recipientFullName;

    @JsonProperty("VolumetricWeight")
    private Double volumetricWeight;

    @JsonProperty("FactualWeight")
    private Double factualWeight;

    @JsonProperty("CitySender")
    private String citySender;

    @JsonProperty("MarketplacePartnerToken")
    private String marketplacePartnerToken;

    @JsonProperty("CounterpartySenderDescription")
    private String counterpartySenderDescription;

    @JsonProperty("InternationalDeliveryType")
    private String internationalDeliveryType;

    @JsonProperty("MoneyTransferCommission")
    private Double moneyTransferCommission;

    @JsonProperty("CargoDescription")
    private String cargoDescription;

    @JsonProperty("SumBeforeCheckWeight")
    private Double sumBeforeCheckWeight;

    @JsonProperty("EndDeliveryTime")
    private String endDeliveryTime;

    @JsonProperty("CheckWeight")
    private Double checkWeight;

    @JsonProperty("Note")
    private String note;

    @JsonProperty("PaymentMethod")
    private String paymentMethod;

    @JsonProperty("CitySenderDescription")
    private String citySenderDescription;

    @JsonProperty("CargoType")
    private String cargoType;

    @JsonProperty("SenderArchive")
    private Boolean senderArchive;

    @JsonProperty("AttorneyDescription")
    private String attorneyDescription;

    @JsonProperty("TypeOfDocument")
    private String typeOfDocument;

    @JsonProperty("PayerType")
    private String payerType;

    @JsonProperty("SenderName")
    private String senderName;

    @JsonProperty("LinkedEWs")
    private List<Object> linkedEWs;

    @JsonProperty("SeatsAmount")
    private Integer seatsAmount;

    @JsonProperty("TrackingStatusCode")
    private Integer trackingStatusCode;

    @JsonProperty("PhoneRecipient")
    private String phoneRecipient;

    @JsonProperty("RecipientAddressDescription")
    private String recipientAddressDescription;

    @JsonProperty("AdjustedDate")
    private String adjustedDate;

    @JsonProperty("CityRecipientDescription")
    private String cityRecipientDescription;

    @JsonProperty("ScheduledDeliveryDate")
    private String scheduledDeliveryDate;

    @JsonProperty("Cost")
    private Double cost;

    @JsonProperty("MoneyTransferCash2Card")
    private Double moneyTransferCash2Card;

    @JsonProperty("MoneyTransferAmount")
    private String moneyTransferAmount;

    @JsonProperty("Number")
    private String number;

    @JsonProperty("RecipientArchive")
    private Boolean recipientArchive;

    @JsonProperty("MoneyTransferPayerCommission")
    private String moneyTransferPayerCommission;

    @JsonProperty("PhoneSender")
    private String phoneSender;

    @JsonProperty("TrackingStatusName")
    private String trackingStatusName;

    @JsonProperty("RefEW")
    private String refEW;

    @JsonProperty("RecipientName")
    private String recipientName;

    @JsonProperty("DocumentCost")
    private Double documentCost;

    @JsonProperty("ScanSheetInternetNumber")
    private String scanSheetInternetNumber;

    @JsonProperty("TrackingUpdateDate")
    private String trackingUpdateDate;

    @JsonProperty("BeginDeliveryTime")
    private String beginDeliveryTime;

    @JsonProperty("CounterpartyRecipient")
    private String counterpartyRecipient;

    @JsonProperty("CargoAutoReturnDate")
    private String cargoAutoReturnDate;

    @JsonProperty("FirstDayStorage")
    private String firstDayStorage;

    @JsonProperty("CardMaskedNumber")
    private String cardMaskedNumber;

    @JsonProperty("CounterpartySender")
    private String counterpartySender;

    @JsonProperty("ReceivingDateTime")
    private String receivingDateTime;

    @JsonProperty("OrderedBackwardDeliveryDocuments")
    private List<Object> orderedBackwardDeliveryDocuments;

    @JsonProperty("MoneyTransferTransactionDate")
    private String moneyTransferTransactionDate;

    @JsonProperty("SettlementSender")
    private String settlementSender;

    @JsonProperty("TransferStatusGlobalMoneyStatus")
    private String transferStatusGlobalMoneyStatus;

    @JsonProperty("SenderAddressDescription")
    private String senderAddressDescription;

    @JsonProperty("MoneyTransferPaymentMethod")
    private String moneyTransferPaymentMethod;

    @JsonProperty("CalculatedWeight")
    private Double calculatedWeight;

    @JsonProperty("ExpressWaybillPaymentStatus")
    private String expressWaybillPaymentStatus;

    @JsonProperty("SettlementRecipient")
    private String settlementRecipient;

    @JsonProperty("LightReturnNumber")
    private String lightReturnNumber;

    @JsonProperty("LightReturn")
    private String lightReturn;

    @JsonProperty("possibilityCreateLightReturn")
    private String possibilityCreateLightReturn;

    @JsonProperty("CounterpartyThirdPerson")
    private String counterpartyThirdPerson;


    @Override
    public String toString() {
        return "<b>Відправлення:</b> " + "<u>" + number + "</u>" +
                "\n<b>Дата створення:</b> " + OutputHelper.formatDateTime(dateTime) +
                "\n<b>Статус:</b> " + trackingStatusName + TrackingStatusColor.getStatusColor(trackingStatusCode).getColor() +
                correctDateOutput() +
                "\n<b>Тип відправлення:</b> " + CargoType.descriptionOf(getCargoType()) +
                "\n<b>Кількість місць:</b> " + seatsAmount +
                "\n<b>Вага:</b> " + documentWeight +
                "\n<b>Відправник:</b>\n" + counterpartySenderDescription +
                "\n" + senderName +
                "\n" + phoneSender +
                "\n" + senderAddressDescription +
                "\n<b>Адреса доставки:</b>\n" + recipientAddressDescription +
                "\n<b>Одержувач:</b>\n" + counterpartyRecipientDescription +
                "\n" + recipientName +
                "\n" + phoneRecipient + "\n";
    }

    private String correctDateOutput() {

        return switch (trackingStatusCode) {
            case 7 -> "\n<b>Дата прибуття: </b>" + OutputHelper.formatDateTime(getArrivalDateTime());
            case 9 -> "\n<b>Дата отримання: </b>" + OutputHelper.formatDateTime(getReceivingDateTime());
            default ->
                    "\n<b>Орієнтовна дата прибуття: </b>" + OutputHelper.formatDateTime(getScheduledDeliveryDate());
        };
    }

}
