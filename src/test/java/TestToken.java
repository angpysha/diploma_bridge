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


import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import io.github.angpysha.diploma_bridge.Helpers.EntitySerializer;
import io.github.angpysha.diploma_bridge.Models.Entity;
import io.github.angpysha.diploma_bridge.Models.Token;
import io.github.angpysha.diploma_bridge.Requests.LoginRequest;
import io.github.angpysha.diploma_bridge.RestClient.RestApiEx2;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestToken {

    @Test
    public void TestEntity() {
        EntitySerializer<Token> tokenEntitySerializer = new EntitySerializer<>();

        String json = "{\n" +
                "    \"access_token\": \"2c98ebf2163f845e340ce95dec71cfd89bfef12b\",\n" +
                "    \"expires_in\": 3600,\n" +
                "    \"token_type\": \"Bearer\",\n" +
                "    \"scope\": null,\n" +
                "    \"refresh_token\": \"6d57ff325b826ca075e63ab7c44969b96ea07a81\"\n" +
                "}";
        Token token = tokenEntitySerializer.JsonToObject(json,Token.class);
        int i = 0;
    }

    @Test
    public void TestGetToken() {
        try {
            Gson gson = new Gson();
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsername("angpyshka");
            loginRequest.setPassword("147852369");
            EntitySerializer<LoginRequest> ser = new EntitySerializer<>();
            String jsn = ser.ObjectToJson(loginRequest);
            RestApiEx2<Entity,LoginRequest> ex2 = new RestApiEx2<>();

            String url = "http://iot:8081/oauth2/token";

//            Map<String,Object> fields = new HashMap<>();
//
//            fields.put("grant_type",loginRequest.getGrantType());
//            fields.put("username",loginRequest.getUsername());
//            fields.put("password",loginRequest.getPassword());
//            fields.put("client_id",loginRequest.getClientId());
//            fields.put("client_secret",loginRequest.getClientSectret());
            Map<String,Object> fields = new HashMap<>();

            fields = ser.ClassToFields(loginRequest,LoginRequest.class);
            HttpResponse<JsonNode> node = ex2.SendPost(url, loginRequest,fields);
            String json_raw = node.getBody().getObject().toString();
            Token token = gson.fromJson(json_raw,Token.class);
            int i =0;
        } catch (Exception ex) {
            int i =0;
        }
    }
}
