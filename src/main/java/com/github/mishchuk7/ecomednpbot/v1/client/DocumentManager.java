package com.github.mishchuk7.ecomednpbot.v1.client;

import com.github.mishchuk7.ecomednpbot.v1.model.SearchRequest;

import java.io.IOException;
import java.util.List;

public interface DocumentManager<T> {

    List<T> getAllDocuments(SearchRequest searchRequest) throws IOException, InterruptedException;

}
