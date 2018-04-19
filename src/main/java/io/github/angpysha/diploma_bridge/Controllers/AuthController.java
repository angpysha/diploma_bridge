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

package io.github.angpysha.diploma_bridge.Controllers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.angpysha.diploma_bridge.Decorators.DateEx;
import io.github.angpysha.diploma_bridge.Models.Entity;
import io.github.angpysha.diploma_bridge.Models.Token;
import io.github.angpysha.diploma_bridge.Requests.LoginRequest;
import io.github.angpysha.diploma_bridge.RestClient.RestApiEx2;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Auth controller for bearer token support
 */
public class AuthController extends BaseController {

    /**
     * <b>Authenticate user.</b> <br/>
     * You can use token in all controller classes as field Token
     * @param request Request, which contains user data
     * @param url Target url
     * @return Operation success
     *
     */
    public boolean Auth(LoginRequest request,String url) {
        try {
            RestApiEx2<Token, LoginRequest> apiEx2 = new RestApiEx2<>();
            // String url = "http://iot:8081/oauth2/token";

            Map<String, Object> fields = new HashMap<>();

            fields.put("grant_type", request.getGrantType());
            fields.put("username", request.getUsername());
            fields.put("password", request.getPassword());
            fields.put("client_id", request.getClientId());
            fields.put("client_secret", request.getClientSectret());
            HttpResponse<JsonNode> node = apiEx2.SendPost(url, request, fields);

            Gson gson = new Gson();

            String node_raw = node.getBody().getObject().toString();
            Token token = gson.fromJson(node_raw,Token.class);
            DateEx dateEx = new DateEx(TokenExpires);

            TokenExpires = dateEx.AddSeconds(token.getExpiresIn());
            Token = token.getAccessToken();
            return true;
        }
          //  HttpResponse<JsonNode> node = apiEx2.SendPost()
        catch (UnirestException e) {
            return false;
            //e.printStackTrace();
        }
    }

    @Override
    public Entity GetAverage(List data, int pos) {
        return null;
    }
}
