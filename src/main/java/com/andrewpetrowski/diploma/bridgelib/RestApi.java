package com.andrewpetrowski.diploma.bridgelib;

import com.andrewpetrowski.diploma.bridgelib.HttpHelpers.Headers;
import com.andrewpetrowski.diploma.bridgelib.HttpHelpers.URLParams;
import com.andrewpetrowski.diploma.bridgelib.Models.Entity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Future;

public class RestApi<T extends Entity> {
    private final String API_URL = "http://diplomaapi:8080/dhts/add";

    public void EnableMapper()
    {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }

            }
        });

    }

    public HttpResponse<JsonNode> SendPost(String url,T data) throws UnirestException
    {
        return Unirest.post(url)
                    .body(data)
                    .asJson();
    }

    public HttpResponse<JsonNode> SendPost(T data) throws UnirestException
    {
        return Unirest.post(API_URL)
                                    .body(data)
                                    .asJson();

    }

    public HttpResponse<JsonNode> SendPost(T data, Headers headers) throws UnirestException
    {
        return Unirest.post(API_URL)
                    .headers(headers.getHeaders())
                    .body(data)
                    .asJson();
    }

    public HttpResponse<JsonNode> SendPost(String url,T data, Headers header) throws UnirestException
    {
        return Unirest.post(url)
                .headers(header.getHeaders())
                .body(data)
                .asJson();
    }

    public HttpResponse<JsonNode> SendPost(String url,T data, Map<String,String> headers) throws UnirestException {
        return Unirest.post(url)
                    .headers(headers)
                    .body(data)
                    .asJson();
    }

    public HttpResponse<JsonNode> SendPost(T data, Map<String,String> headers) throws UnirestException
    {
        return  Unirest.post(API_URL)
                        .headers(headers)
                        .body(data)
                        .asJson();
    }

    public HttpResponse<JsonNode> SendPost(String url,T data, Map<String,String> headers,URLParams params) throws UnirestException
    {
        url = url + params.toString();
        return  Unirest.post(url)
                .headers(headers)
                .body(data)
                .asJson();
    }

    public HttpResponse<JsonNode> SendPost(String url,T data,Headers headers,URLParams params) throws UnirestException
    {
        url = url + params.toString();
        return Unirest.post(url)
                    .headers(headers.getHeaders())
                    .body(data)
                    .asJson();
    }

    public HttpResponse<JsonNode> SendPost(T data, Map<String,String> headers, URLParams urlParams) throws UnirestException {
        String newURL = API_URL + urlParams.toString();

        return Unirest.post(newURL)
                    .headers(headers)
                    .body(data)
                    .asJson();
    }

    public HttpResponse<JsonNode> SendPost(T data, Headers headers, URLParams urlParams) throws UnirestException {
        String newURL = API_URL + urlParams.toString();

        return Unirest.post(newURL)
                .headers(headers.getHeaders())
                .body(data)
                .asJson();
    }



    public Future<HttpResponse<JsonNode>> SendPostAsync(T data) throws UnirestException {
        return Unirest.post(API_URL)
                      .body(data)
                        .asJsonAsync();
    }
    
}
