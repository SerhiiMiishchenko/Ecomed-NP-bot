package com.github.mishchuk7.ecomednpbot.v1.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Slf4j
public class HttpRequestUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10)).build();

    private HttpRequestUtils() {
    }

    public static <T> T post(String uri, Object request, Class<T> valueType) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .POST(getBodyPublisher(request))
                .header("Content-Type", "application/json");
        return send(valueType, builder);
    }

    private static HttpRequest.BodyPublisher getBodyPublisher(Object request) throws JsonProcessingException {
        return HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(request));
    }

    private static <T> T send(Class<T> valueType, HttpRequest.Builder builder) throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(builder.build(), HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            log.error(response.statusCode() + " : " + response.body());
        }
        return objectMapper.readValue(response.body(), valueType);
    }
}
