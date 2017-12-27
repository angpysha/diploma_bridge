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

package com.andrewpetrowski.diploma.bridgelib.HttpHelpers;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class is light implementation of headers
 * @author Andrew Petrowsky
 * @version 1.0
 */
public class Headers {
    private Map<String,String> headers = new LinkedHashMap<>();
    private Map.Entry<String,String> headersEntry;

    public void Add(String key, String value)
    {
        headers.put(key,value);
    }

    /**
     * This function gets http headers
     * @return Return all headers as <b>Map</b>
     */
    public Map<String,String> getHeaders() {
        return headers;
    }

    /**
     * Sets headers from <b>Map</b> object
     * @param headers Headers as <b>Map</b>
     */
    public void setHeaders(Map<String,String> headers)
    {
        this.headers = headers;
    }
}
