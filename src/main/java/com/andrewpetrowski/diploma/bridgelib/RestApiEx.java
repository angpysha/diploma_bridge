/*
 *    Copyright 2017 Andrew Petrowsky
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
     * @throws UnirestException Error was occurred, when send query
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
     * @throws UnirestException Error was occurred, when send query
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
     * @throws UnirestException Error was occurred, when send query
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
     * @throws UnirestException Error was occurred, when send query
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
     * @throws UnirestException Error was occurred, when send query
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
     * @throws UnirestException Error was occurred, when send query
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(String url,U data,Headers headers) throws UnirestException {
        return Unirest.post(url)
                    .headers(headers.getHeaders())
                    .body(data)
                    .asJsonAsync();
    }

    //endregion


}
