package com.github.mishchuk7.ecomednpbot.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class MethodProperties {

    @JsonIgnore
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @JsonProperty("FindByData")
    private String findByData;

    @JsonProperty("Page")
    private int page = 0;

    @JsonProperty("Limit")
    private int limit = 300;

    @JsonProperty("DateFrom")
    private final String dateFrom = LocalDate.now().minusMonths(3L).format(formatter);

    @JsonProperty("DateTo")
    private final String dateTo = LocalDate.now().plusMonths(2L).format(formatter);

    @JsonProperty("Documents")
    private List<Document> documents;

    public MethodProperties(String findByData) {
        this.findByData = findByData;
    }

    public MethodProperties(List<Document> documents) {
        this.documents = documents;
    }

    public static class Document {

        @JsonProperty("DocumentNumber")
        private String documentNumber;

        @JsonProperty("Phone")
        private String phone;

        public Document(String documentNumber, String phone) {
            this.documentNumber = documentNumber;
            this.phone = phone;
        }

        public String getDocumentNumber() {
            return documentNumber;
        }

        public String getPhone() {
            return phone;
        }
    }

}
