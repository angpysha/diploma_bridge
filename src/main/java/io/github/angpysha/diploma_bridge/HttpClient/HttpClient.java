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

package io.github.angpysha.diploma_bridge.HttpClient;

import com.google.gson.Gson;
import okhttp3.*;
import okhttp3.internal.http2.Header;
import org.apache.http.client.methods.RequestBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Authenticator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class HttpClient {

    private static OkHttpClient _httpClient = new OkHttpClient();
    private static Gson gson = new Gson();
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public static <T> T SendRequest(String URL, Class<T> responsetype, ERequestType requestType, Object data,
                                    Map<String,String> headers) {


        Request.Builder requestBuilder = new Request.Builder().url(URL);

        for (Map.Entry<String ,String> header : headers.entrySet()) {
            requestBuilder.addHeader(header.getKey(),header.getValue());
        }

        if (requestType == ERequestType.GET) {
            Request request = requestBuilder.get().build();

            return getT(responsetype, request);
        }

        if (requestType == ERequestType.POST) {
            if (data != null) {
                String json = gson.toJson(data);
                RequestBody requestBody = RequestBody.create(JSON, json);
                Request request = requestBuilder.post(requestBody).build();

                return getT(responsetype, request);
            } else {
                RequestBody requestBody = RequestBody.create(JSON,"");
               Request request = requestBuilder.post(null).build();

               return getT(responsetype,request);
            }
        }

        if (requestType == ERequestType.EDIT) {
            if (data != null) {
                String json = gson.toJson(data);
                RequestBody requestBody = RequestBody.create(JSON, json);
                Request request = requestBuilder.put(requestBody).build();

                return getT(responsetype, request);
            } else {
                RequestBody requestBody = RequestBody.create(JSON,"");
                Request request = requestBuilder.put(null).build();

                return getT(responsetype,request);
            }
        }

        if (requestType == ERequestType.DELETE) {
            if (data != null) {
                String json = gson.toJson(data);
                RequestBody requestBody = RequestBody.create(JSON, json);
                Request request = requestBuilder.delete(requestBody).build();

                return getT(responsetype, request);
            } else {
                RequestBody requestBody = RequestBody.create(JSON, "");
                Request request = requestBuilder.delete(null).build();

                return getT(responsetype, request);
            }
        }

        return null;

    }

    private static <T> T getT(Class<T> responsetype, Request request) {
        try (Response response = _httpClient.newCall(request).execute()) {
            String responseJson = response.body().string();
            T resonseObject = gson.fromJson(responseJson,responsetype);
            return resonseObject;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> CompletableFuture<T> SendRequestAsync(String URL, Class<T> responsetype, ERequestType requestType, Object data, Map<String,String> headers) throws IOException {
        Request.Builder builder = new Request.Builder().url(URL);

        for (Map.Entry<String, String > header : headers.entrySet()) {
            builder.addHeader(header.getKey(),header.getValue());
        }

        if (requestType == ERequestType.GET) {
            Request request = builder.get().build();
            return gettCompletableFuture(responsetype, request);
        }


        if (requestType == ERequestType.POST) {


            String json = gson.toJson(data);

            RequestBody requestBody = RequestBody.create(JSON,json);

            Request request = builder.post(requestBody).build();
            return gettCompletableFuture(responsetype, request);
        }

        if (requestType == ERequestType.EDIT) {


            String json = gson.toJson(data);

            RequestBody requestBody = RequestBody.create(JSON,json);

            Request request = builder.put(requestBody).build();
            return gettCompletableFuture(responsetype, request);
        }

        if (requestType == ERequestType.DELETE) {


            String json = gson.toJson(data);

            RequestBody requestBody = RequestBody.create(JSON,json);

            Request request = builder.delete(requestBody).build();
            return gettCompletableFuture(responsetype, request);
        }

        return null;

    }

    private static <T> CompletableFuture<T> gettCompletableFuture(Class<T> responsetype, Request request) {
        final CompletableFuture<T> promise = new CompletableFuture<>();

        _httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                promise.complete(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String ResponseString = response.body().string();
                T responseObject = gson.fromJson(ResponseString,responsetype);
                promise.complete(responseObject);
            }
        });

        return promise;
    }
}
