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

/**
 * This class provides Rest API support for this project.
 * There is used <i><b>Unirest Java library</b></i> for simplify HTTP Requests
 * @param <T> Model class
 * @author Andrew Petrowsky
 * @version 0.2
 */
public class RestApi<T extends Entity> {
    private final String API_URL = "http://diplomaapi:8080/dhts/add";

    /**
     * Default class constructor
     */
    public RestApi()
    {
        this.EnableMapper();
    }

    /**
     * <b>Enable object mapper.</b>
     *
     * With the help of this method we can map object to class instance and to json
     */
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

 //region POST request

    /**
     * Send POST request
     * @param url Site URL
     * @param data Request data
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(String url,T data) throws UnirestException
    {
        return Unirest.post(url)
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     * @param data Request data
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(T data) throws UnirestException
    {
        return Unirest.post(API_URL)
                .body(data)
                .asJson();

    }

    /**
     * Send POST request
     * @param data Request data
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(T data, Headers headers) throws UnirestException
    {
        return Unirest.post(API_URL)
                .headers(headers.getHeaders())
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     * @param url Site URL
     * @param data Request data
     * @param header Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(String url,T data, Headers header) throws UnirestException
    {
        return Unirest.post(url)
                .headers(header.getHeaders())
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     * @param url Site URL
     * @param data Request data
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(String url,T data, Map<String,String> headers) throws UnirestException {
        return Unirest.post(url)
                .headers(headers)
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     * @param data Request data
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(T data, Map<String,String> headers) throws UnirestException
    {
        return  Unirest.post(API_URL)
                .headers(headers)
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     * @param url Site URL
     * @param data Request data
     * @param headers Request headers
     * @param params URL parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(String url,T data, Map<String,String> headers,URLParams params) throws UnirestException
    {
        url = url + params.toString();
        return  Unirest.post(url)
                .headers(headers)
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     * @param url Site URL
     * @param data Request data
     * @param headers Request headers
     * @param params URL parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(String url,T data,Headers headers,URLParams params) throws UnirestException
    {
        url = url + params.toString();
        return Unirest.post(url)
                .headers(headers.getHeaders())
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     * @param data Request data
     * @param headers Request headers
     * @param urlParams URL parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(T data, Map<String,String> headers, URLParams urlParams) throws UnirestException {
        String newURL = API_URL + urlParams.toString();

        return Unirest.post(newURL)
                .headers(headers)
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     * @param data Request data
     * @param headers Request headers
     * @param urlParams URL parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(T data, Headers headers, URLParams urlParams) throws UnirestException {
        String newURL = API_URL + urlParams.toString();

        return Unirest.post(newURL)
                .headers(headers.getHeaders())
                .body(data)
                .asJson();
    }



    /**
     * Send POST request async
     * @param data Request data
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(T data) throws UnirestException {
        return Unirest.post(API_URL)
                      .body(data)
                        .asJsonAsync();
    }

    /**
     * Send POST request async
     * @param url Site URL
     * @param data Request data
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(String url,T data) throws UnirestException {
        return Unirest.post(url)
                    .body(data)
                    .asJsonAsync();
    }

    /**
     * Send POST request async
     * @param data Request data
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(T data, Headers headers) throws UnirestException
    {
        return Unirest.post(API_URL)
                .headers(headers.getHeaders())
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     * @param url Site URL
     * @param data Request data
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(String url,T data, Headers header) throws UnirestException
    {
        return Unirest.post(url)
                .headers(header.getHeaders())
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     * @param url Site URL
     * @param data Request data
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(String url,T data, Map<String,String> headers) throws UnirestException {
        return Unirest.post(url)
                .headers(headers)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     * @param data Request data
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(T data, Map<String,String> headers) throws UnirestException
    {
        return  Unirest.post(API_URL)
                .headers(headers)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     * @param url Site URL
     * @param data Request data
     * @param headers Request headers
     * @param params URL parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(String url,T data, Map<String,String> headers,URLParams params) throws UnirestException
    {
        url = url + params.toString();
        return  Unirest.post(url)
                .headers(headers)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     * @param url Site URL
     * @param data Request data
     * @param headers Request headers
     * @param params URL parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(String url,T data,Headers headers,URLParams params) throws UnirestException
    {
        url = url + params.toString();
        return Unirest.post(url)
                .headers(headers.getHeaders())
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     * @param data Request data
     * @param headers Request headers
     * @param urlParams URL parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(T data, Map<String,String> headers, URLParams urlParams) throws UnirestException {
        String newURL = API_URL + urlParams.toString();

        return Unirest.post(newURL)
                .headers(headers)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     * @param data Request data
     * @param headers Request headers
     * @param urlParams URL parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(T data, Headers headers, URLParams urlParams) throws UnirestException {
        String newURL = API_URL + urlParams.toString();

        return Unirest.post(newURL)
                .headers(headers.getHeaders())
                .body(data)
                .asJsonAsync();
    }

    //endregion

    //region GET request
    public HttpResponse<JsonNode> SendGet(String url) throws UnirestException {
        return Unirest.get(url)
                    .asJson();
    }

    public HttpResponse<JsonNode> SendGet(String url, Headers headers) throws UnirestException {
        return Unirest.get(url)
                    .headers(headers.getHeaders())
                    .asJson();
    }

    public HttpResponse<JsonNode> SendGet(String url,Map<String,String> headers) throws UnirestException {
        return Unirest.get(url)
                    .headers(headers)
                    .asJson();
    }

    public HttpResponse<JsonNode> SendGet(String url,Headers headers, URLParams params) throws UnirestException {
       url = url + params.toString();
        return Unirest.get(url)
                .headers(headers.getHeaders())
                .asJson();
    }

    public HttpResponse<JsonNode> SendGet(String url,Map<String,String> headers,URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.get(url)
                    .headers(headers)
                    .asJson();
    }
    

    //endregion

}
