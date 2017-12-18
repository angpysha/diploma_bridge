package com.andrewpetrowski.diploma.bridgelib;

import com.andrewpetrowski.diploma.bridgelib.Models.Entity;
import com.andrewpetrowski.diploma.bridgelib.Models.SearchEntity;
import com.andrewpetrowski.diploma.bridgelib.RestApi;
import com.andrewpetrowski.diploma.bridgelib.HttpHelpers.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.concurrent.Future;

/**
 * Extended class for REST API. This one can help to split search model from database model.
 * @param <T> Database model which extends from <b>Entity</b> class
 * @param <U> Search model which extends from <b>SearchEntity</b> class
 * @see Entity
 * @see SearchEntity
 * @author Andrew Petrowsky
 * @version 0.1
 */
public class RestApiEx<T extends Entity,U extends SearchEntity> extends RestApi<T> {

    /**
     * Dummy constructor
     */
    public RestApiEx() {
        super();
    }

    //region POST request

    /**
     * Send POST request
     * @param url Site URL
     * @param data Filter data
     * @return Json element
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(String url,U data) throws UnirestException {
        return Unirest.post(url)
                    .body(data)
                    .asJson();
    }

    /**
     * Send POST request
     * @param url Site URL
     * @param data Filter data
     * @param headers Request headers
     * @return Json element
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(String url, U data, Headers headers) throws UnirestException {
        return Unirest.post(url)
                    .headers(headers.getHeaders())
                    .body(data)
                    .asJson();
    }


    /**
     *Send POST request
     * @param data Filter data
     * @return Json element
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(U data) throws UnirestException {
        return Unirest.post(API_URL)
                    .body(data)
                    .asJson();
    }

    /**
     * Send POST request
     * @param url Site URL
     * @param data Filter data
     * @return Json element
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(String url, U data) throws UnirestException {
        return Unirest.post(url)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request
     * @param data Filter data
     * @return Json element
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(U data) throws UnirestException {
        return Unirest.post(API_URL)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request
     * @param url Site URL
     * @param data Filter data
     * @param headers Request headers
     * @return Json element
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(String url,U data,Headers headers) throws UnirestException {
        return Unirest.post(url)
                    .headers(headers.getHeaders())
                    .body(data)
                    .asJsonAsync();
    }

    //endregion


}
