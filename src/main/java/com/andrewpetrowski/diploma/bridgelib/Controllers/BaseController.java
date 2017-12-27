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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Basic actions to API
 *
 * @param <T> Database model which extends from <b>Entity</b> class
 * @param <U> Search model which extends from <b>SearchEntity</b> class
 * @author Andrew Petrowsky
 * @version 0.4
 * @see Entity
 * @see SearchEntity
 */
public class BaseController<T extends Entity, U extends SearchEntity> {

    /**
     * Date format for right serialization
     */
    protected SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * Object mapper for object serialization
     */
    protected ObjectMapper mapper = new ObjectMapper();

    /**
     * Server url
     */
    protected String BASE_URL = "http://diplomaapi:8080";

    /**
     * Url for Get function
     */
    protected String GET_URL = "";

    /**
     * Url for Search function
     */
    protected String SEARCH_URL = "";

    /**
     * Url for Add function
     */
    protected String ADD_URL = "";

    /**
     * Url for Delete function
     */
    protected String DELETE_URL = "";

    /**
     * Url for Update function
     */
    protected String UPDATE_URL = "";


    /**
     * Private member needed to async execution
     */
    private ExecutorService service = Executors.newSingleThreadExecutor();

    /**
     * Dummy constructor
     */
    public BaseController() {
        this.mapper.setDateFormat(df);
    }

    /**
     * Search entities in database
     *
     * @param searchFilter Search filter class
     * @param entityClass  Entity model
     * @return <b>List<T></b> which contains models
     */
    public List<T> Search(U searchFilter, Class<? extends Entity> entityClass) {
        try {
            RestApiEx<T, U> searchAp = new RestApiEx<>();

            HttpResponse<JsonNode> dataa = searchAp.SendPostAsync(SEARCH_URL, searchFilter).get();

            String tmpStr = dataa.getBody()
                    .getArray()
                    .toString();

            List<T> list = mapper.readValue(tmpStr, mapper.getTypeFactory().constructCollectionType(List.class, entityClass.getClass()));

            return list;
        } catch (UnirestException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        } catch (InterruptedException e) {
            return null;
        } catch (ExecutionException e) {
            return null;
        }

    }


    /**
     * Get object by id
     *
     * @param id <b>Integer</b> value, which equals database <i>id</i> column
     * @param className Class to cast
     * @return Casted class instance
     */
    public T Get(int id, Class<T> className) {
        try {
            RestApi<T> restApi = new RestApi<>();
            //      String gg = SEARCH_URL + "/" + Integer.toString(id);
            HttpResponse<JsonNode> response = restApi.SendPostAsync(BASE_URL + "/" + GET_URL + "/" + Integer.toString(id), null).get();

            String tmpStr = response.getBody()
                    .getObject()
                    .toString();


            return mapper.readValue(tmpStr, className);
        } catch (UnirestException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        } catch (InterruptedException e) {
            return null;
        } catch (ExecutionException e) {
            return null;
        }
    }


    /**
     * Get all objects in table
     *
     * @param className Class to cast
     * @return Object list which contains all entries of table
     */
    public T GetAll(Class<? extends Entity> className) {
        //TODO: NOT implemendted
        return null;
    }

    /**
     * Add new row to database
     *
     * @param model Table model
     * @return <b>Boolean</b> result of operation result
     */
    public boolean Add(T model) {
        try {
            RestApi<T> restApi = new RestApi<>();
            HttpResponse<JsonNode> response = restApi.SendPostAsync(BASE_URL + "/" + ADD_URL, model).get();
            String tmpStr = response.getBody()
                    .getObject()
                    .get("result")
                    .toString();

            return mapper.readValue(tmpStr, Boolean.class);

        } catch (UnirestException ex) {
            return false;

        } catch (IOException ex) {
            return false;
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e) {
            return false;
        }
    }

    /**
     * Delete an entry from table
     *
     * @param model Object entry
     * @return Operation success
     */
    public boolean Remove(T model) {
        try {
            RestApi<T> restApi = new RestApi<>();
            HttpResponse<JsonNode> response = restApi.SendDeleteAsync(String.format("%d/%d", BASE_URL, DELETE_URL), model.getId()).get();
            String tmpStr = response.getBody()
                    .getObject()
                    .get("result")
                    .toString();

            return mapper.readValue(tmpStr, boolean.class);
        } catch (UnirestException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e) {
            return false;
        }
    }

    /**
     * Delete an entry from table
     *
     * @param id Object id
     * @return Operation success
     */
    public boolean Remove(int id) {
        try {
            RestApi<T> restApi = new RestApi<>();
            HttpResponse<JsonNode> response = restApi.SendDeleteAsync(String.format("%s/%s", BASE_URL, DELETE_URL), id).get();
            String tmpStr = response.getBody()
                    .getObject()
                    .get("result")
                    .toString();

            return mapper.readValue(tmpStr, boolean.class);
        } catch (UnirestException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e) {
            return false;
        }
    }

    /**
     * Update table entry
     *
     * @param id    Object id
     * @param model Entry model
     * @return Operation success
     */
    public boolean Update(int id, T model) {
        try {
            RestApi<T> restApi = new RestApi<>();
            HttpResponse<JsonNode> response = restApi.SendPutAsync(String.format("%s/%s/%d", BASE_URL, UPDATE_URL, id), model).get();
            String tmpStr = response.getBody()
                    .getObject()
                    .get("result")
                    .toString();

            return mapper.readValue(tmpStr, boolean.class);
        } catch (UnirestException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e) {
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Update table entry
     *
     * @param id    Object id
     * @param model Entry model
     * @return Operation success
     */
    public Future<Boolean> UpdateAsync(int id, T model) {
        return service.submit(() -> {
            return this.Update(id, model);
        });
    }

    /**
     * Delete an entry from table
     *
     * @param id Object id
     * @return Operation success
     */
    public Future<Boolean> RemoveAsync(int id) {
        return service.submit(() -> {
            return this.Remove(id);
        });
    }

    /**
     * Delete an entry from table
     *
     * @param model Object model
     * @return Operation success
     */
    public Future<Boolean> RemoveAsync(T model) {
        return service.submit(() -> {
            return this.Remove(model);
        });
    }

    /**
     * Add new row to database
     *
     * @param model Table model
     * @return <b>Boolean</b> result of operation result
     */
    public Future<Boolean> AddAsync(T model) {
        return service.submit(() -> {
            return this.Add(model);
        });
    }

    /**
     * Search entities in database
     *
     * @param filter Search filter class
     * @param entityClass  Entity model
     * @return <b>List<T></b> which contains models
     */
    public Future<List<T>> SearchAsync(U filter, Class<? extends Entity> entityClass) {
        return service.submit(() -> {
            return this.Search(filter, entityClass);
        });
    }

    /**
     * Get object by id
     *
     * @param id        <b>Integer</b> value, which equals database <i>id</i> column
     * @param tClass Class to cast
     * @return Casted class instance
     */
    public Future<T> GetAsync(int id, Class<T> tClass) {
        return service.submit(() -> {
            return this.Get(id, tClass);
        });
    }


}
