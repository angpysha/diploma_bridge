package com.andrewpetrowski.diploma.bridgelib.Controllers;

import com.andrewpetrowski.diploma.bridgelib.Models.DHT11_Data;
import com.andrewpetrowski.diploma.bridgelib.Models.Entity;
import com.andrewpetrowski.diploma.bridgelib.Models.SearchEntity;
import com.andrewpetrowski.diploma.bridgelib.RestApi;
import com.andrewpetrowski.diploma.bridgelib.RestApiEx;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Basic actions to API
 * @param <T> Database model which extends from <b>Entity</b> class
 * @param <U> Search model which extends from <b>SearchEntity</b> class
 * @see Entity
 * @see SearchEntity
 * @author Andrew Petrowsky
 * @version 0.2
 */
public class BaseController<T extends Entity,U extends SearchEntity> {
    protected SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    protected ObjectMapper mapper = new ObjectMapper();

    protected String SEARCH_URL = "";

    public BaseController() {
        this.mapper.setDateFormat(df);
    }

    public List<T> Search(U searchFilter,Class<? extends Entity> entityClass) throws UnirestException,IOException {
        RestApiEx<T,U> searchAp = new RestApiEx<>();

        HttpResponse<JsonNode> dataa = searchAp.SendPost(SEARCH_URL, searchFilter);

        String tmpStr = dataa.getBody()
                .getArray()
                .toString();

        List<T> list = mapper.readValue(tmpStr, mapper.getTypeFactory().constructCollectionType(List.class, entityClass.getClass()));

        return list;
    }
}
