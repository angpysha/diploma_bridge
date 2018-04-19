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

package io.github.angpysha.diploma_bridge.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.github.angpysha.diploma_bridge.Annotations.FieldProperty;

public class LoginRequest extends ApiRequest {
    @JsonProperty("grant_type")
    @SerializedName("grant_type")
    @FieldProperty("grant_type")
    private String GrantType = "password";

    public String getGrantType() {
        return GrantType;
    }

    public void setGrantType(String grandType) {
        GrantType = grandType;
    }

    @JsonProperty("username")
    @SerializedName("username")
    @FieldProperty("username")
    private String Username;


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    @JsonProperty("password")
    @SerializedName("password")
    @FieldProperty("password")
    private String Password;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @JsonProperty("client_id")
    @SerializedName("client_id")
    @FieldProperty("client_id")
    private String ClientId = "testclient";


    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    @SerializedName("client_secret")
    @JsonProperty("client_secret")
    @FieldProperty("client_secret")
    private String ClientSectret = "testpass";


    public String getClientSectret() {
        return ClientSectret;
    }

    public void setClientSectret(String clientSectret) {
        ClientSectret = clientSectret;
    }
}
