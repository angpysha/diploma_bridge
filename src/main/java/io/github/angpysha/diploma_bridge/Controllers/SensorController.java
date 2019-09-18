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

import io.github.angpysha.diploma_bridge.HttpClient.ERequestType;
import io.github.angpysha.diploma_bridge.HttpClient.HttpClient;

import java.io.IOException;

public class SensorController {


    private static String BASE_URL = "http://localhost:8080/api/v1/sensors";
    public static String GetAll() {
        String url = BASE_URL+"/getall";
        try {
            String data = HttpClient.SendRequestJson(url, ERequestType.GET,"",null);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String GetById(String id) {
        String url = BASE_URL+"/get/" +id;
        try {
            String data = HttpClient.SendRequestJson(url,ERequestType.GET,"",null);
            return data;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String Add(String json) {
        String url = BASE_URL+"/add";
        try {
            String data = HttpClient.SendRequestJson(url,ERequestType.POST,json,null);
            return data;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String Update(String json,String id) {
        String url = BASE_URL+"/update/"+id;
        try {
            String data = HttpClient.SendRequestJson(url,ERequestType.EDIT,json,null);
            return data;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String Delete(String id) {
        String url = BASE_URL+"/delete/"+id;
        try {
            String data = HttpClient.SendRequestJson(url,ERequestType.DELETE,null,null);
            return data;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String Search(String json) {
        String url = BASE_URL+"/search";
        try {
            String data = HttpClient.SendRequestJson(url,ERequestType.POST,json,null);
            return data;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
