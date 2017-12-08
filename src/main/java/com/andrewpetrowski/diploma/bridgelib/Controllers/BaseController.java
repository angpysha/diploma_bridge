package com.andrewpetrowski.diploma.bridgelib.Controllers;

import com.andrewpetrowski.diploma.bridgelib.Models.DHT11_Data;
import com.andrewpetrowski.diploma.bridgelib.Models.Entity;
import com.andrewpetrowski.diploma.bridgelib.Models.SearchEntity;
import com.andrewpetrowski.diploma.bridgelib.RestApi;
import com.andrewpetrowski.diploma.bridgelib.RestApiEx;
import com.fasterxml.jackson.core.type.TypeReference;
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
 *
 * @param <T> Database model which extends from <b>Entity</b> class
 * @param <U> Search model which extends from <b>SearchEntity</b> class
 * @author Andrew Petrowsky
 * @version 0.2
 * @see Entity
 * @see SearchEntity
 */
public class BaseController<T extends Entity, U extends SearchEntity> {
    protected SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    protected ObjectMapper mapper = new ObjectMapper();
    protected String BASE_URL = "http://diplomaapi:8080";
    protected String GET_URL = "/";
    protected String SEARCH_URL = "";
    protected String ADD_URL;


    /**
     * Dummy construcotrf
     */
    public BaseController() {
        this.mapper.setDateFormat(df);
    }

    public List<T> Search(U searchFilter, Class<? extends Entity> entityClass) {
        try {
            RestApiEx<T, U> searchAp = new RestApiEx<>();

            HttpResponse<JsonNode> dataa = searchAp.SendPost(SEARCH_URL, searchFilter);

            String tmpStr = dataa.getBody()
                    .getArray()
                    .toString();

            List<T> list = mapper.readValue(tmpStr, mapper.getTypeFactory().constructCollectionType(List.class, entityClass.getClass()));

            return list;
        } catch (UnirestException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }

    }


    public T Get(int id, Class<T> className) {
        try {
            RestApi<T> restApi = new RestApi<>();
            //      String gg = SEARCH_URL + "/" + Integer.toString(id);
            HttpResponse<JsonNode> response = restApi.SendPost(BASE_URL + "/" + GET_URL + "/" + Integer.toString(id), null);

            String tmpStr = response.getBody()
                    .getObject()
                    .toString();


            return mapper.readValue(tmpStr, className);
        } catch (UnirestException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }


    public T GetAll(Class<? extends Entity> className) {
        //TODO: NOT implemendted
        return null;
    }

    public boolean Add(T model) {
        try {
            RestApi<T> restApi = new RestApi<>();
            HttpResponse<JsonNode> response = restApi.SendPost(BASE_URL + "/" + ADD_URL, model);
            String tmpStr = response.getBody()
                    .getObject()
                    .get("result")
                    .toString();

            return mapper.readValue(tmpStr, Boolean.class);

        } catch (UnirestException ex) {
            return false;

        } catch (IOException ex) {
            return false;
        }
    }


}
