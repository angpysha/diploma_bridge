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

import com.andrewpetrowski.diploma.bridgelib.HttpHelpers.Headers;
import com.andrewpetrowski.diploma.bridgelib.HttpHelpers.URLParams;
import com.andrewpetrowski.diploma.bridgelib.Models.Entity;
import com.andrewpetrowski.diploma.bridgelib.Models.SearchEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.HTTP;
import org.json.JSONObject;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Dictionary;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * This class provides Rest API support for this project.
 * There is used <i><b>Unirest Java library</b></i> for simplify HTTP Requests
 *
 * @param <T> Database model which extends from <b>Entity</b> class
 * @see Entity
 * @author Andrew Petrowsky
 * @version 0.8
 */
public class RestApi<T extends Entity> {
    protected final String API_URL = "http://diplomaapi:8080/dhts/add";

    /**
     * Default class constructor
     */
    public RestApi() {
        this.EnableMapper();
    }

    /**
     * <b>Enable object mapper.</b>
     * <p>
     * With the help of this method we can map object to class instance and to json
     */
    public void EnableMapper() {
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
     *
     * @param url  Site URL
     * @param data Request data
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(String url, T data) throws UnirestException {
        return Unirest.post(url)
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     *
     * @param data Request data
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(T data) throws UnirestException {
        return Unirest.post(API_URL)
                .body(data)
                .asJson();

    }

    /**
     * Send POST request
     *
     * @param data    Request data
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(T data, Headers headers) throws UnirestException {
        return Unirest.post(API_URL)
                .headers(headers.getHeaders())
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     *
     * @param url    Site URL
     * @param data   Request data
     * @param header Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(String url, T data, Headers header) throws UnirestException {
        return Unirest.post(url)
                .headers(header.getHeaders())
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     *
     * @param url     Site URL
     * @param data    Request data
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(String url, T data, Map<String, String> headers) throws UnirestException {
        return Unirest.post(url)
                .headers(headers)
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     *
     * @param data    Request data
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(T data, Map<String, String> headers) throws UnirestException {
        return Unirest.post(API_URL)
                .headers(headers)
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     *
     * @param url     Site URL
     * @param data    Request data
     * @param headers Request headers
     * @param params  URL parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(String url, T data, Map<String, String> headers, URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.post(url)
                .headers(headers)
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     *
     * @param url     Site URL
     * @param data    Request data
     * @param headers Request headers
     * @param params  URL parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(String url, T data, Headers headers, URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.post(url)
                .headers(headers.getHeaders())
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     *
     * @param data      Request data
     * @param headers   Request headers
     * @param urlParams URL parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(T data, Map<String, String> headers, URLParams urlParams) throws UnirestException {
        String newURL = API_URL + urlParams.toString();

        return Unirest.post(newURL)
                .headers(headers)
                .body(data)
                .asJson();
    }

    /**
     * Send POST request
     *
     * @param data      Request data
     * @param headers   Request headers
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
     *
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
     *
     * @param url  Site URL
     * @param data Request data
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(String url, T data) throws UnirestException {
        return Unirest.post(url)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     *
     * @param data    Request data
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(T data, Headers headers) throws UnirestException {
        return Unirest.post(API_URL)
                .headers(headers.getHeaders())
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     *
     * @param url  Site URL
     * @param data Request data
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(String url, T data, Headers header) throws UnirestException {
        return Unirest.post(url)
                .headers(header.getHeaders())
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     *
     * @param url     Site URL
     * @param data    Request data
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(String url, T data, Map<String, String> headers) throws UnirestException {
        return Unirest.post(url)
                .headers(headers)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     *
     * @param data    Request data
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(T data, Map<String, String> headers) throws UnirestException {
        return Unirest.post(API_URL)
                .headers(headers)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     *
     * @param url     Site URL
     * @param data    Request data
     * @param headers Request headers
     * @param params  URL parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(String url, T data, Map<String, String> headers, URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.post(url)
                .headers(headers)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     *
     * @param url     Site URL
     * @param data    Request data
     * @param headers Request headers
     * @param params  URL parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(String url, T data, Headers headers, URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.post(url)
                .headers(headers.getHeaders())
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     *
     * @param data      Request data
     * @param headers   Request headers
     * @param urlParams URL parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPostAsync(T data, Map<String, String> headers, URLParams urlParams) throws UnirestException {
        String newURL = API_URL + urlParams.toString();

        return Unirest.post(newURL)
                .headers(headers)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send POST request async
     *
     * @param data      Request data
     * @param headers   Request headers
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

    /**
     * Send GET request
     *
     * @param url Site URL
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendGet(String url) throws UnirestException {
        return Unirest.get(url)
                .asJson();
    }

    /**
     * Send GET request
     *
     * @param url     Site URL
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendGet(String url, Headers headers) throws UnirestException {
        return Unirest.get(url)
                .headers(headers.getHeaders())
                .asJson();
    }

    /**
     * Send GET request
     *
     * @param url     Site URL
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendGet(String url, Map<String, String> headers) throws UnirestException {
        return Unirest.get(url)
                .headers(headers)
                .asJson();
    }

    /**
     * Send GET request
     *
     * @param url     Site URL
     * @param headers Request headers
     * @param params  Request parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendGet(String url, Headers headers, URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.get(url)
                .headers(headers.getHeaders())
                .asJson();
    }

    /**
     * Send GET request
     *
     * @param url     Site URL
     * @param headers Request headers
     * @param params  Request parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendGet(String url, Map<String, String> headers, URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.get(url)
                .headers(headers)
                .asJson();
    }

    //region async

    /**
     * Send GET request async
     *
     * @param url Site URL
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendGetAsync(String url) throws UnirestException {
        return Unirest.get(url)
                .asJsonAsync();
    }

    /**
     * Send GET request async
     *
     * @param url     Site URL
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendGetAsync(String url, Headers headers) throws UnirestException {
        return Unirest.get(url)
                .headers(headers.getHeaders())
                .asJsonAsync();
    }

    /**
     * Send GET request async
     *
     * @param url     Site URL
     * @param headers Request headers
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendGetAsync(String url, Map<String, String> headers) throws UnirestException {
        return Unirest.get(url)
                .headers(headers)
                .asJsonAsync();
    }

    /**
     * Send GET request
     *
     * @param url     Site URL
     * @param headers Request headers
     * @param params  Request parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendGetAsync(String url, Headers headers, URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.get(url)
                .headers(headers.getHeaders())
                .asJsonAsync();
    }

    /**
     * Send GET request
     *
     * @param url     Site URL
     * @param headers Request headers
     * @param params  Request parameters
     * @return Server response as json object
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendGetAsync(String url, Map<String, String> headers, URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.get(url)
                .headers(headers)
                .asJsonAsync();
    }

    //endregion

    //endregion

    //region PUT request

    /**
     * Send PUT request
     *
     * @param url  Site URL
     * @param data Request data
     * @return Server response as JSON
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPut(String url, T data) throws UnirestException {
        return Unirest.put(url)
                .body(data)
                .asJson();
    }

    /**
     * Send PUT request
     *
     * @param data Request data
     * @return Server response as JSON
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPut(T data) throws UnirestException {
        return Unirest.put(API_URL)
                .body(data)
                .asJson();
    }

    /**
     * Send PUT request
     *
     * @param url     Site URL
     * @param data    Request data
     * @param headers Request headers
     * @return Server response as JSON
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPut(String url, T data, Headers headers) throws UnirestException {
        return Unirest.put(url)
                .headers(headers.getHeaders())
                .body(data)
                .asJson();
    }

    /**
     * Send PUT request
     *
     * @param url     Site URL
     * @param data    Request data
     * @param headers Request headers
     * @return Server response as JSON
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPut(String url, T data, Map<String, String> headers) throws UnirestException {
        return Unirest.put(url)
                .headers(headers)
                .body(data)
                .asJson();
    }

    /**
     * Send PUT request
     * @param data Request data
     * @param headers Request headers
     * @return Server response as JSON
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPut(T data, Headers headers) throws UnirestException {
        return Unirest.put(API_URL)
                .headers(headers.getHeaders())
                .body(data)
                .asJson();
    }

    /**
     * Send PUT request
     * @param data Request data
     * @param headers Request headers
     * @return Server response as JSON
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPut(T data, Map<String, String> headers) throws UnirestException {
        return Unirest.put(API_URL)
                .headers(headers)
                .body(data)
                .asJson();
    }

    /**
     * Send PUT request
     * @param url Site URL
     * @param data Request data
     * @param headers Request headers
     * @param params Request parameters
     * @return Server response as JSON
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPut(String url, T data, Headers headers, URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.put(url)
                .headers(headers.getHeaders())
                .body(data)
                .asJson();
    }

    /**
     * Send PUT request
     *
     * @param url     Site URL
     * @param data    Request data
     * @param headers Request headers
     * @param params  Request parameters
     * @return Server response as JSON
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPut(String url, T data, Map<String, String> headers, URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.put(url)
                .headers(headers)
                .body(data)
                .asJson();
    }

    /**
     * Send PUT request
     * @param data Request data
     * @param headers Request headers
     * @param params Request parameters
     * @return Server response as JSON
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPut(T data, Headers headers, URLParams params) throws UnirestException {
        String url = API_URL + params.toString();
        return Unirest.put(url)
                .headers(headers.getHeaders())
                .body(data)
                .asJson();
    }

    /**
     * Send PUT request
     * @param data Request data
     * @param headers Request headers
     * @param params Request parameters
     * @return Server response as JSON
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPut(T data, Map<String, String> headers, URLParams params) throws UnirestException {
        String url = API_URL + params.toString();
        return Unirest.put(url)
                .headers(headers)
                .body(data)
                .asJson();
    }

    /**
     * Send PUT request
     * @param data Request data
     * @param params Request parameters
     * @return Server response as JSON
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPut(T data, URLParams params) throws UnirestException {
        String url = API_URL + params.toString();
        return Unirest.put(url)
                .body(data)
                .asJson();
    }

    /**
     * Send PUT request
     * @param url Site URL
     * @param data Request data
     * @param params Request parameters
     * @return Server response as JSON
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPut(String url, T data, URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.put(url)
                .body(data)
                .asJson();
    }

    //async

    /**
     * Send PUT request async
     *
     * @param url  Site URL
     * @param data Request data
     * @return Server response as JSON
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPutAsync(String url, T data) throws UnirestException {
        return Unirest.put(url)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send PUT request async
     *
     * @param data Request data
     * @return Server response as JSON
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPutAsync(T data) throws UnirestException {
        return Unirest.put(API_URL)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send PUT request async
     *
     * @param url     Site URL
     * @param data    Request data
     * @param headers Request headers
     * @return Server response as JSON
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPutAsync(String url, T data, Headers headers) throws UnirestException {
        return Unirest.put(url)
                .headers(headers.getHeaders())
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send PUT request async
     *
     * @param url     Site URL
     * @param data    Request data
     * @param headers Request headers
     * @return Server response as JSON
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPutAsync(String url, T data, Map<String, String> headers) throws UnirestException {
        return Unirest.put(url)
                .headers(headers)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send PUT request async
     * @param data Request data
     * @param headers Request headers
     * @return Server response as JSON
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPutAsync(T data, Headers headers) throws UnirestException {
        return Unirest.put(API_URL)
                .headers(headers.getHeaders())
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send PUT request async
     * @param data Request data
     * @param headers Request headers
     * @return Server response as JSON
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPutAsync(T data, Map<String, String> headers) throws UnirestException {
        return Unirest.put(API_URL)
                .headers(headers)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send PUT request async
     * @param url Site URL
     * @param data Request data
     * @param headers Request headers
     * @param params Request parameters
     * @return Server response as JSON
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPutAsync(String url, T data, Headers headers, URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.put(url)
                .headers(headers.getHeaders())
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send PUT request async
     *
     * @param url     Site URL
     * @param data    Request data
     * @param headers Request headers
     * @param params  Request parameters
     * @return Server response as JSON
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPutAsync(String url, T data, Map<String, String> headers, URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.put(url)
                .headers(headers)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send PUT request async
     * @param data Request data
     * @param headers Request headers
     * @param params Request parameters
     * @return Server response as JSON
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPutAsync(T data, Headers headers, URLParams params) throws UnirestException {
        String url = API_URL + params.toString();
        return Unirest.put(url)
                .headers(headers.getHeaders())
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send PUT request async
     * @param data Request data
     * @param headers Request headers
     * @param params Request parameters
     * @return Server response as JSON
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPutAsync(T data, Map<String, String> headers, URLParams params) throws UnirestException {
        String url = API_URL + params.toString();
        return Unirest.put(url)
                .headers(headers)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send PUT request async
     * @param data Request data
     * @param params Request parameters
     * @return Server response as JSON
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPutAsync(T data, URLParams params) throws UnirestException {
        String url = API_URL + params.toString();
        return Unirest.put(url)
                .body(data)
                .asJsonAsync();
    }

    /**
     * Send PUT request async
     * @param url Site URL
     * @param data Request data
     * @param params Request parameters
     * @return Server response as JSON
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendPutAsync(String url, T data, URLParams params) throws UnirestException {
        url = url + params.toString();
        return Unirest.put(url)
                .body(data)
                .asJsonAsync();
    }

    //endregion

    //region DELETE request

    /**
     * Send DELETE request
     * @param url Site url
     * @param id Object id
     * @return  Json value, which contains the result of operation;
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendDelete(String url,int id) throws UnirestException {
        url = String.format("%s/%d",url,id);
        return Unirest.delete(url)
                .asJson();
    }

    /**
     * Send DELETE request
     * @param url Site url
     * @param id Object id
     * @param headers Request headers
     * @return  Json value, which contains the result of operation;
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendDelete(String url,int id, Headers headers) throws UnirestException {
        url = String.format("%s/%d",url,id);
        return Unirest.delete(url)
                    .headers(headers.getHeaders())
                    .asJson();
    }

    /**
     * Send DELETE request
     * @param url Site url
     * @param id Object id
     * @param headers Request headers
     * @return  Json value, which contains the result of operation;
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendDelete(String url, int id, Map<String,String> headers) throws UnirestException {
        url = String.format("%s/%d",url,id);
        return Unirest.delete(url)
                    .headers(headers)
                    .asJson();
    }

    /**
     * Send DELETE request
     * @param url Site url
     * @param id Object id
     * @param headers Request headers
     * @param params  Request URL parameters
     * @return  Json value, which contains the result of operation;
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendDelete(String url,int id,Headers headers,URLParams params) throws UnirestException {
        url = String.format("%s/%d",url,id);
        url = url + params.toString();
        return Unirest.delete(url)
                .headers(headers.getHeaders())
                .asJson();
    }

    /**
     * Send DELETE request
     * @param url Site url
     * @param id Object id
     * @param headers Request headers
     * @param params  Request URL parameters
     * @return  Json value, which contains the result of operation;
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendDelete(String url, int id, Map<String,String> headers,URLParams params) throws UnirestException {
        url = String.format("%s/%d",url,id);
        url = String.format("%s%s",url,params.toString());
        return Unirest.delete(url)
                    .headers(headers)
                    .asJson();
    }

    //async
    /**
     * Send DELETE request
     * @param url Site url
     * @param id Object id
     * @return  Json value, which contains the result of operation;
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendDeleteAsync(String url,int id) throws UnirestException {
        url = String.format("%s/%d",url,id);
        return Unirest.delete(url)
                .asJsonAsync();
    }

    /**
     * Send DELETE request
     * @param url Site url
     * @param id Object id
     * @param headers Request headers
     * @return  Json value, which contains the result of operation;
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendDeleteAsync(String url,int id, Headers headers) throws UnirestException {
        url = String.format("%s/%d",url,id);
        return Unirest.delete(url)
                .headers(headers.getHeaders())
                .asJsonAsync();
    }

    /**
     * Send DELETE request
     * @param url Site url
     * @param id Object id
     * @param headers Request headers
     * @return  Json value, which contains the result of operation;
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendDeleteAsync(String url, int id, Map<String,String> headers) throws UnirestException {
        url = String.format("%s/%d",url,id);
        return Unirest.delete(url)
                .headers(headers)
                .asJsonAsync();
    }

    /**
     * Send DELETE request
     * @param url Site url
     * @param id Object id
     * @param headers Request headers
     * @param params  Request URL parameters
     * @return  Json value, which contains the result of operation;
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendDeleteAsync(String url,int id,Headers headers,URLParams params) throws UnirestException {
        url = String.format("%s/%d",url,id);
        url = url + params.toString();
        return Unirest.delete(url)
                .headers(headers.getHeaders())
                .asJsonAsync();
    }

    /**
     * Send DELETE request
     * @param url Site url
     * @param id Object id
     * @param headers Request headers
     * @param params  Request URL parameters
     * @return  Json value, which contains the result of operation;
     * @throws UnirestException
     */
    public Future<HttpResponse<JsonNode>> SendDeleteAsync(String url, int id, Map<String,String> headers,URLParams params) throws UnirestException {
        url = String.format("%s/%d",url,id);
        url = String.format("%s%s",url,params.toString());
        return Unirest.delete(url)
                .headers(headers)
                .asJsonAsync();
    }
    //endasync

    //endregion

}


