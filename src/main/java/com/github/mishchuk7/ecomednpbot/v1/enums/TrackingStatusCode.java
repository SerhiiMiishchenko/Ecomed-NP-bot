package com.github.mishchuk7.ecomednpbot.v1.enums;

import static com.github.mishchuk7.ecomednpbot.v1.enums.TrackingStatusColor.*;

public enum TrackingStatusCode {

    SENDER_CREATED(1, "Відправник самостійно створив цю накладну, але ще не надав до відправки", GREY),
    DELETED(2, "Видалено", RED),
    NUMBER_NOT_FOUND(3, "Номер не знайдено", RED),
    SENT_TO_CITY(4, "Відправлення у місті ХХXХ. (Статус для межобластных отправлений)", GREY),
    SENT_TO_LOCAL_CITY(41, "Відправлення у місті ХХXХ. (Статус для услуг локал стандарт и локал экспресс - доставка в пределах города)", GREY),
    SENT_TO_DESTINATION(5, "Відправлення прямує до міста YYYY", BLUE),
    SENT_TO_DESTINATION_BRANCH(6, "Відправлення у місті YYYY, орієнтовна доставка до ВІДДІЛЕННЯ-XXX dd-mm. Очікуйте додаткове повідомлення про прибуття", BLUE),
    ARRIVED_AT_BRANCH(7, "Прибув на відділення", ORANGE),
    ARRIVED_AT_BRANCH_POSTMACHINE(8, "Прибув на відділення (завантажено в Поштомат)", ORANGE),
    RECEIVED(9, "Відправлення отримано", GREEN),
    RECEIVED_WITH_SMS_NOTIFICATION(10, "Відправлення отримано %DateReceived%. Протягом доби ви одержите SMS-повідомлення про надходження грошового переказу та зможете отримати його в касі відділення «Нова пошта»", GREEN),
    RECEIVED_WITH_MONEY_TRANSFER(11, "Відправлення отримано %DateReceived%. Грошовий переказ видано одержувачу.", GREEN),
    PROCESSING(12, "Нова Пошта комплектує ваше відправлення", GREY),
    EN_ROUTE_TO_RECIPIENT(101, "На шляху до одержувача", BLUE),
    RECIPIENT_REFUSED(102, "Відмова від отримання (Відправником створено замовлення на повернення)", RED),
    RECIPIENT_REFUSED_BY_RECIPIENT(103, "Відмова одержувача (отримувач відмовився від відправлення)", RED),
    ADDRESS_MODIFIED(104, "Змінено адресу", GREEN),
    STORAGE_DISCONTINUED(105, "Припинено зберігання", RED),
    RECEIVED_AND_RETURN_SHIPMENT_CREATED(106, "Одержано і створено ЄН зворотньої доставки", GREEN),
    DELIVERY_UNSUCCESSFUL(111, "Невдала спроба доставки через відсутність Одержувача на адресі або зв'язку з ним", ORANGE),
    DELIVERY_DATE_POSTPONED(112, "Дата доставки перенесена Одержувачем", ORANGE);

    final int id;
    final String description;
    final TrackingStatusColor color;


    TrackingStatusCode(int id, String description, TrackingStatusColor color) {
        this.id = id;
        this.description = description;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public TrackingStatusColor getColor() {
        return color;
    }
}
