/*
 *    Copyright 2018 Andrew Petrowsky
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

package io.github.angpysha.diploma_bridge.RestClient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.MultipartBody;
import com.mashape.unirest.request.body.RequestBodyEntity;
import io.github.angpysha.diploma_bridge.Models.Entity;
import io.github.angpysha.diploma_bridge.Requests.ApiRequest;
import io.github.angpysha.diploma_bridge.RestApi;

import java.util.HashMap;
import java.util.Map;

/**
 *  Used for send request with some addidtional paramenters {@link ApiRequest}
 * @param <T> {@link Entity} class
 * @param <U> {@link ApiRequest} class
 */
public class RestApiEx2<T extends Entity,U extends ApiRequest> extends RestApi<T> {

    private RequestBodyEntity entity;

    /**
     * Dummy constructor
     */
    public RestApiEx2() {
        super();
    }

    /**
     * Send post request
     * @param url Target url
     * @param request request data as {@link ApiRequest} class
     * @param fields Request fields
     * @return Entity object
     * @throws UnirestException
     */
    public HttpResponse<JsonNode> SendPost(String url, U request, Map<String,Object> fields) throws UnirestException{
        MultipartBody entity = Unirest.post(url)
                .fields(fields);
        return entity
                    .asJson();
      //  return null;
    }

    @Deprecated
    /**
     * Send post request
     * @param url Target url
     * @param request request data as json string
     * @return Entity object
     * @throws UnirestException
     * @deprecated Not using use other methods insteed
     */
    public HttpResponse<JsonNode> SendPost(String url, String request) throws UnirestException {
        return Unirest.post(url)
                .body(request)
                .asJson();
    }
}
