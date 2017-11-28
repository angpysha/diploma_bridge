package com.andrewpetrowski.diploma.bridgelib.HttpHelpers;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  Simple implementation of URLParameters
 *  @author Andrew Petrowsky
 *  @version 1.0
 */
public class URLParams {
    private Map<String,String> params = new LinkedHashMap<>();
    private Map.Entry<String,String> last;

    /**
     * Get URL parameters
     * @return Return <b>Map</b> which contains all parameters
     */
    public Map<String,String> getParams()
    {
        return this.params;
    }

    /**
     * Create instance of URLParams class
     */
    public URLParams()
    {

    }

    /**
     * Create new instance of URLParams class
     * @param params <b>Map</b> which contains params
     */
    public URLParams(Map<String,String> params)
    {
        this.params = params;
    }

    public void Add(String key,String value)
    {
        params.put(key,value);
    }

    /**
     * Convert URLParams object to string
     * @return URL like params string
     */
    public static final String FromURLParametesToString(URLParams parameters)
    {
        if (parameters == null)
            return "";
        String retValue = "";
        retValue+="?";
        parameters.last = parameters.getLast();
        for (Map.Entry entry : parameters.getParams().entrySet())
        {
            retValue+=String.format("%s=%s",entry.getKey(),entry.getValue());
            if (parameters.last.getKey() != entry.getKey())
                retValue+="&";
        }
        return retValue;
    }

    /**
     * Convert URLParams object to string
     * @return URL like params string
     */
    @Override
    public String toString()
    {
        if (this == null)
            return "";
        String retValue = "";
        retValue+="?";
        this.last = this.getLast();
        for (Map.Entry entry : this.getParams().entrySet())
        {
            retValue+=String.format("%s=%s",entry.getKey(),entry.getValue());
            if (this.last.getKey() != entry.getKey())
                retValue+="&";
        }
        return retValue;
    }

    /**
     * Convert String parameters to URLParameters object
     * @param paramsStr URL like parameters string
     * @return URLParams class instance, which contains all string's data
     */
    public static final URLParams FromStringToURLPArameters(String paramsStr)
    {
        URLParams parameters = new URLParams();
        paramsStr=paramsStr.replace("?","");
        String[] paramEntries = paramsStr.split("&");
        for (String entry :paramEntries)
        {
            String[] KeyValPair = entry.split("=");
            parameters.Add(KeyValPair[0],KeyValPair[1]);
        }
        return parameters;
    }

    /**
     * Get last parameter
     * @return <b>Map.Entry</b> which contain parameter
     */
    public Map.Entry<String,String> getLast() {
        Iterator<Map.Entry<String,String>> iterator = this.params.entrySet().iterator();
        Map.Entry<String,String> result = null;
        while (iterator.hasNext()) {
            result = iterator.next();
        }
        return result;
    }
}
