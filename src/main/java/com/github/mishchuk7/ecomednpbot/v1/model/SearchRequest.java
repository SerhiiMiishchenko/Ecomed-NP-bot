package com.github.mishchuk7.ecomednpbot.v1.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@NonNull
public class SearchRequest {

    private String modelName;
    private String apiKey;
    private String calledMethod;
    private MethodProperties methodProperties;

}
