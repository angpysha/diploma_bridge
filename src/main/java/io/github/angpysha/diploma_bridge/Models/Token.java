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

package io.github.angpysha.diploma_bridge.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class Token extends Entity {
    @JsonProperty("access_token")
    @SerializedName("access_token")
    private String AccessToken;

    @JsonProperty("expires_in")
    @SerializedName("expires_in")
    private int ExpiresIn;

    @JsonProperty("token_type")
    @SerializedName("token_type")
    private String TokenType;

    @JsonProperty("scope")
    @SerializedName("scope")
    private String Scope;

    @JsonProperty("refresh_token")
    @SerializedName("refresh_token")
    private String RefreshToken;

    public Token() {
    }


    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }


    public int getExpiresIn() {
        return ExpiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        ExpiresIn = expiresIn;
    }


    public String getTokenType() {
        return TokenType;
    }

    public void setTokenType(String tokenType) {
        TokenType = tokenType;
    }


    public String getScope() {
        return Scope;
    }

    public void setScope(String scope) {
        Scope = scope;
    }


    public String getRefreshToken() {
        return RefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        RefreshToken = refreshToken;
    }
}
