package com.github.mishchuk7.ecomednpbot.v1.model;

import com.github.mishchuk7.ecomednpbot.v1.enums.SearchButton;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserSession {

    private Long chatId;
    private SearchButton searchButton;
    private String data;

}
