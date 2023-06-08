package com.github.mishchuk7.ecomednpbot.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.mishchuk7.ecomednpbot.v1.enums.PayerType;
import com.github.mishchuk7.ecomednpbot.v1.enums.PaymentMethod;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackingDocument {

    @JsonProperty("DocumentWeight")
    private double documentWeight;

    @JsonProperty("ServiceType")
    private String serviceType;

    @JsonProperty("LoyaltyCardSender")
    private String loyaltyCardSender;

    @JsonProperty("RecipientFullName")
    private String recipientFullName;

    @JsonProperty("FactualWeight")
    private String factualWeight;

    @JsonProperty("MarketplacePartnerToken")
    private String marketplacePartnerToken;

    @JsonProperty("CounterpartySenderDescription")
    private String counterpartySenderDescription;

    @JsonProperty("InternationalDeliveryType")
    private String internationalDeliveryType;

    @JsonProperty("CargoType")
    private String cargoType;

    @JsonProperty("PayerType")
    private String payerType;

    @JsonProperty("SeatsAmount")
    private String seatsAmount;

    @JsonProperty("ScheduledDeliveryDate")
    private String scheduledDeliveryDate;

    @JsonProperty("DocumentCost")
    private String documentCost;

    @JsonProperty("CardMaskedNumber")
    private String cardMaskedNumber;

    @JsonProperty("OwnerDocumentType")
    private String ownerDocumentType;

    @JsonProperty("ExpressWaybillPaymentStatus")
    private String expressWaybillPaymentStatus;

    @JsonProperty("ExpressWaybillAmountToPay")
    private int expressWaybillAmountToPay;

    @JsonProperty("AfterpaymentOnGoodsCost")
    private int afterpaymentOnGoodsCost;

    @JsonProperty("SumBeforeCheckWeight")
    private int sumBeforeCheckWeight;

    @JsonProperty("CheckWeight")
    private int checkWeight;

    @JsonProperty("PaymentMethod")
    private String paymentMethod;

    @JsonProperty("AdjustedDate")
    private String adjustedDate;

    @JsonProperty("Number")
    private String number;

    @JsonProperty("PhoneSender")
    private String phoneSender;

    @JsonProperty("TrackingUpdateDate")
    private String trackingUpdateDate;

    @JsonProperty("CalculatedWeight")
    private String calculatedWeight;

    @JsonProperty("WarehouseRecipient")
    private String warehouseRecipient;

    @JsonProperty("WarehouseSender")
    private String warehouseSender;

    @JsonProperty("DateCreated")
    private String dateCreated;

    @JsonProperty("DateScan")
    private String dateScan;

    @JsonProperty("DateReturnCargo")
    private String dateReturnCargo;

    @JsonProperty("DateMoving")
    private String dateMoving;

    @JsonProperty("DateFirstDayStorage")
    private String dateFirstDayStorage;

    @JsonProperty("DatePayedKeeping")
    private String datePayedKeeping;

    @JsonProperty("RecipientAddress")
    private String recipientAddress;

    @JsonProperty("RecipientDateTime")
    private String recipientDateTime;

    @JsonProperty("RefCityRecipient")
    private String refCityRecipient;

    @JsonProperty("RefCitySender")
    private String refCitySender;

    @JsonProperty("RefSettlementRecipient")
    private String refSettlementRecipient;

    @JsonProperty("RefSettlementSender")
    private String refSettlementSender;

    @JsonProperty("SenderAddress")
    private String senderAddress;

    @JsonProperty("SenderFullNameEW")
    private String senderFullNameEW;

    @JsonProperty("ClientBarcode")
    private String clientBarcode;

    @JsonProperty("CitySender")
    private String citySender;

    @JsonProperty("CityRecipient")
    private String cityRecipient;

    @JsonProperty("CargoDescriptionString")
    private String cargoDescriptionString;

    @JsonProperty("AnnouncedPrice")
    private int announcedPrice;

    @JsonProperty("AdditionalInformationEW")
    private String additionalInformationEW;

    @JsonProperty("ActualDeliveryDate")
    private String actualDeliveryDate;

    @JsonProperty("StatusCode")
    private Integer statusCode;
    @JsonProperty("PostomatV3CellReservationNumber")
    private boolean postomatV3CellReservationNumber;
    @JsonProperty("AmountToPay")
    private String amountToPay;
    @JsonProperty("AmountPaid")
    private String amountPaid;
    @JsonProperty("RefEW")
    private String refEW;
    @JsonProperty("VolumeWeight")
    private String volumeWeight;
    @JsonProperty("CheckWeightMethod")
    private String checkWeightMethod;
    @JsonProperty("OwnerDocumentNumber")
    private String ownerDocumentNumber;
    @JsonProperty("LastCreatedOnTheBasisNumber")
    private String lastCreatedOnTheBasisNumber;
    @JsonProperty("LastCreatedOnTheBasisDateTime")
    private String lastCreatedOnTheBasisDateTime;
    @JsonProperty("LastTransactionDateTimeGM")
    private String lastTransactionDateTimeGM;
    @JsonProperty("PaymentStatus")
    private String paymentStatus;
    @JsonProperty("PaymentStatusDate")
    private String paymentStatusDate;
    @JsonProperty("LastAmountTransferGM")
    private String lastAmountTransferGM;
    @JsonProperty("LastAmountReceivedCommissionGM")
    private int lastAmountReceivedCommissionGM;
    @JsonProperty("LastCreatedOnTheBasisPayerType")
    private String lastCreatedOnTheBasisPayerType;
    @JsonProperty("DeliveryTimeframe")
    private String deliveryTimeframe;
    @JsonProperty("LastTransactionStatusGM")
    private String lastTransactionStatusGM;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("Redelivery")
    private int redelivery;
    @JsonProperty("RedeliveryNum")
    private String redeliveryNum;
    @JsonProperty("RedeliverySum")
    private int redeliverySum;
    @JsonProperty("RedeliveryPayer")
    private String redeliveryPayer;
    @JsonProperty("UndeliveryReasonsDate")
    private String undeliveryReasonsDate;
    @JsonProperty("UndeliveryReasonsSubtypeDescription")
    private String undeliveryReasonsSubtypeDescription;
    @JsonProperty("RecipientWarehouseTypeRef")
    private String recipientWarehouseTypeRef;
    @JsonProperty("WarehouseRecipientInternetAddressRef")
    private String warehouseRecipientInternetAddressRef;
    @JsonProperty("WarehouseRecipientNumber")
    private int warehouseRecipientNumber;
    @JsonProperty("WarehouseRecipientRef")
    private String warehouseRecipientRef;
    @JsonProperty("CategoryOfWarehouse")
    private String categoryOfWarehouse;
    @JsonProperty("WarehouseRecipientAddress")
    private String warehouseRecipientAddress;
    @JsonProperty("WarehouseSenderInternetAddressRef")
    private String warehouseSenderInternetAddressRef;
    @JsonProperty("WarehouseSenderAddress")
    private String warehouseSenderAddress;
    @JsonProperty("CounterpartyType")
    private String counterpartyType;
    @JsonProperty("CounterpartySenderType")
    private String counterpartySenderType;
    @JsonProperty("AviaDelivery")
    private int aviaDelivery;
    @JsonProperty("BarcodeRedBox")
    private String barcodeRedBox;
    @JsonProperty("CargoReturnRefusal")
    private boolean cargoReturnRefusal;
    @JsonProperty("DaysStorageCargo")
    private String daysStorageCargo;
    @JsonProperty("Packaging")
    private List<String> packaging;
    @JsonProperty("PartialReturnGoods")
    private List<String> partialReturnGoods;
    @JsonProperty("SecurePayment")
    private boolean securePayment;
    @JsonProperty("StorageAmount")
    private String storageAmount;
    @JsonProperty("StoragePrice")
    private String storagePrice;

    @JsonProperty("PossibilityCreateRedirecting")
    private boolean possibilityCreateRedirecting;

    @JsonProperty("PossibilityCreateReturn")
    private boolean possibilityCreateReturn;

    @JsonProperty("PossibilityCreateRefusal")
    private boolean possibilityCreateRefusal;

    @JsonProperty("PossibilityChangeEW")
    private boolean possibilityChangeEW;

    @JsonProperty("PossibilityChangeCash2Card")
    private boolean possibilityChangeCash2Card;
    @JsonProperty("PossibilityChangeDeliveryIntervals")
    private boolean possibilityChangeDeliveryIntervals;
    @JsonProperty("PossibilityTrusteeRecipient")
    private boolean possibilityTrusteeRecipient;
    @JsonProperty("TrusteeRecipientPhone")
    private String trusteeRecipientPhone;
    @JsonProperty("CounterpartyRecipientDescription")
    private String counterpartyRecipientDescription;
    @JsonProperty("CreatedOnTheBasis")
    private String createdOnTheBasis;
    @JsonProperty("FreeShipping")
    private String freeShipping;
    @JsonProperty("InternetDocumentDescription")
    private String internetDocumentDescription;
    @JsonProperty("LastCreatedOnTheBasisDocumentType")
    private String lastCreatedOnTheBasisDocumentType;
    @JsonProperty("LoyaltyCardRecipient")
    private String loyaltyCardRecipient;
    @JsonProperty("PhoneRecipient")
    private String phoneRecipient;
    @JsonProperty("RecipientFullNameEW")
    private String recipientFullNameEW;
    @JsonProperty("RedeliveryPaymentCardDescription")
    private String redeliveryPaymentCardDescription;
    @JsonProperty("RedeliveryServiceCost")
    private String redeliveryServiceCost;
    @JsonProperty("RedeliveryPaymentCardRef")
    private String redeliveryPaymentCardRef;

    @JsonProperty("LightReturnNumber")
    private String lightReturnNumber;

    @Override
    public String toString() {
        return "<b>Вартість доставки:</b> " + documentCost + " грн"
                + "\n<b>Платник за доставку:</b> " + PayerType.descriptionOf(payerType)
                + " / " + PaymentMethod.descriptionOf(paymentMethod)
                + "\n<b>Статус доставки:</b> " + status;
    }
}
