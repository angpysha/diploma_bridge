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
