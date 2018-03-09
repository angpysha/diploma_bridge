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

import io.github.angpysha.diploma_bridge.Decorators.DateEx;
import io.github.angpysha.diploma_bridge.Models.DisplayPeriod;
import io.github.angpysha.diploma_bridge.RestApiEx;
import io.github.angpysha.diploma_bridge.Models.Entity;
import io.github.angpysha.diploma_bridge.Models.SearchEntity;
import io.github.angpysha.diploma_bridge.RestApi;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static io.github.angpysha.diploma_bridge.Models.DisplayPeriod.*;

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
public abstract class BaseController<T extends Entity, U extends SearchEntity> {

    private Gson gson;
    /**
     * Date format for right serialization
     */
    protected SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Object mapper for object serialization
     */
    protected ObjectMapper mapper = new ObjectMapper();

    /**
     * Server url
     */
    protected String BASE_URL = "http://rasp.kl.com.ua/web";
//     protected String BASE_URL = "http://diplomaapi:8080";


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
     * URL for Get last element
     */

    protected String GET_LAST_URL = "";

    /**
     * URL for get datesCount
     */
    protected String GET_SIZE_URL = "";

    protected String GET_FIRST_URL = "";

    protected String GET_MAX_MIN_DATES_URL = "";
    /**
     * Private member needed to async execution
     */
    private ExecutorService service = Executors.newSingleThreadExecutor();

    /**
     * Dummy constructor
     */
    public BaseController() {
        this.mapper.setDateFormat(df);
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

    }


    /**
     * Search entities in database
     *
     * @param searchFilter Search filter class
     * @param entityClass  Entity model
     * @return <b>List</b> which contains models
     */
    public List<T> Search(U searchFilter, Class<? extends Entity> entityClass) {
        try {
            RestApiEx<T, U> searchAp = new RestApiEx<>();

            HttpResponse<JsonNode> dataa = searchAp.SendPostAsync(BASE_URL + SEARCH_URL, searchFilter).get();

            String tmpStr = dataa.getBody()
                    .getArray()
                    .toString();
            //List<T> list = gson.fromJson(tmpStr,new TypeToken<List<DHT11_Data>>(){}.getType());
            List<T> list = mapper.readValue(tmpStr, mapper.getTypeFactory().constructCollectionType(List.class, entityClass));
            return list;
        } catch (UnirestException ex) {
            return null;
        } catch (InterruptedException e) {
            return null;
        } catch (ExecutionException e) {
            return null;
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Integer GetDatesCount() {
        try {
            RestApi<T> restApi = new RestApi<>();
            HttpResponse<JsonNode> node = restApi.SendPost(BASE_URL + GET_SIZE_URL, null);

            String tmpsStr = node.getBody()
                    .getObject()
                    .get("pages")
                    .toString();

            return mapper.readValue(tmpsStr, Integer.class);
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Get object by id
     *
     * @param id        <b>Integer</b> value, which equals database <i>id</i> column
     * @param className Class to cast
     * @return Casted class instance
     */
    public T Get(int id, Class<T> className) {
        try {
            RestApi<T> restApi = new RestApi<>();
            //      String gg = SEARCH_URL + "/" + Integer.toString(id);
            HttpResponse<JsonNode> response = restApi.SendPostAsync(BASE_URL + GET_URL + "/" + Integer.toString(id), null).get();

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
            HttpResponse<JsonNode> response = restApi.SendPostAsync(BASE_URL + ADD_URL, model).get();
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
            //  e.printStackTrace();
        } catch (ExecutionException e) {
            return false;
            //e.printStackTrace();
        }
    }

    /**
     * Add list of objects to database
     *
     * @param data List of obects
     * @return Operation result
     */
    public Boolean Add(List<T> data) {
        RestApi<T> api = new RestApi<>();

        List<Boolean> res = new LinkedList<>();

        data.forEach(it -> {
            HttpResponse<JsonNode> response = null;
            try {
                response = api.SendPostAsync(BASE_URL + ADD_URL, it).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
            String tmpStr = response.getBody()
                    .getObject()
                    .get("result")
                    .toString();

            try {
                res.add(mapper.readValue(tmpStr, Boolean.class));
            } catch (IOException e) {

            }
        });

        return !res.contains(false);

    }

    /**
     *  Gets and sorts date by some period
     * @param date {@link Date} to sort (must be date in period)
     * @param period Period modifier (for more see {@link DisplayPeriod}
     * @param tClass Model class <b>must extends from {@link Entity} </b>
     * @param uClass Search class <b>must extends from {@link SearchEntity}</b>
     * @return Data sorted by some period
     */
    public List<T> GetByPeriod(Date date, DisplayPeriod period, Class<T> tClass, Class<U> uClass) {
        //TODO: Implement
        Class[] args = new Class[2];
        args[0] = Date.class;
        args[1] = Date.class;
        Date begin = new DateEx(date).ZeroTime();
        switch (period) {
            case DAY: {
                Date after = new DateEx(begin).Increment();
                try {
                    U filter = uClass.getDeclaredConstructor(args).newInstance(begin, after);
                    List<T> data = SearchAsync(filter, tClass).get();
                    return data;
                } catch (InstantiationException | InterruptedException |
                        ExecutionException | NoSuchMethodException |
                        IllegalAccessException | InvocationTargetException e) {
                    return null;
                }
            }
            case WEEK: {
                Date tmp = begin;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                List<T> data;
                int dayOfWeek = DateEx.GetLocalWeekDay(calendar.get(Calendar.DAY_OF_WEEK),
                        calendar.getFirstDayOfWeek());
                data = FilterData(dayOfWeek, tmp, tClass, uClass, args);
                return data;
            }
            case MONTH: {
                Date tmp = begin;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                List<T> data;
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                data = FilterData(dayOfMonth, tmp, tClass, uClass, args);
                return data;
            }
            case YEAR: {
                Date tmp = begin;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                List<T> data;
                int dayOfMonth = calendar.get(Calendar.MONTH);
                data = FilterData(dayOfMonth,tmp,tClass,uClass,args);
                return data;
            }

        }
        return null;
    }

    /**
     * Takes all date in container with some criteria
     * @param numElems Number of {@link List} elements
     * @param date Search {@link Date}
     * @param tClass Model class <b>must extends from {@link Entity} </b>
     * @param uClass Search class <b>must extends from {@link SearchEntity}</b>
     * @param args Constructor arguments array
     * @return {@link List} with some criteria
     */
    private List<T> FilterData(int numElems, Date date, Class<T> tClass, Class<U> uClass, Class[] args) {
        List<T> data = new ArrayList<>();
        for (int i = 0; i < numElems; i++) {
            Date after = new DateEx(date).Increment();
            try {
                U filter = uClass.getDeclaredConstructor(args).newInstance(date, after);
                List<T> dat = SearchAsync(filter, tClass).get();
                T aver = GetAverage(dat,i);
                data.add(aver);
                date = new DateEx(date).Decrement();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | InterruptedException | ExecutionException e) {
//                        e.printStackTrace();
            }
        }

        return data;
    }

    /**
     * Get average value of List (must be {@code List<? extends Entity>})
     * @param data List, needed to get average
     * @param pos Page position
     * @return Average value
     */
    public abstract T GetAverage(List<T> data,int pos);

    /**
     * Delete an entry from table
     *
     * @param model Object entry
     * @return Operation success
     */
    public boolean Remove(T model) {
        try {
            RestApi<T> restApi = new RestApi<>();
            HttpResponse<JsonNode> response = restApi.SendDeleteAsync(String.format("%s%s", BASE_URL, DELETE_URL), model.getId()).get();
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

    public Date[] GetMinMaxDate() throws UnirestException {
        try {
            RestApi<T> restApi = new RestApi<>();
            HttpResponse<JsonNode> response = restApi.SendPostAsync(String.format("%s%s",BASE_URL,GET_MAX_MIN_DATES_URL),
                    null).get();
            String dateMin = response.getBody()
                    .getObject()
                    .get("min")
                    .toString();
            String dateMax = response.getBody()
                    .getObject()
                    .get("max")
                    .toString();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date_min = df.parse(dateMin);
            Date date_max = df.parse(dateMax);

            Date[] dates = new Date[2];

            dates[0] = date_min;
            dates[1] = date_max;

            return  dates;
        } catch (InterruptedException | ExecutionException | ParseException e ) {
            e.printStackTrace();
            return null;
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
            HttpResponse<JsonNode> response = restApi.SendDeleteAsync(String.format("%s%s", BASE_URL, DELETE_URL), id).get();
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
     * Gets last table entry
     *
     * @param className Model class
     * @return Last table entity entry
     */
    public T GetLast(Class<T> className) {
       return GetLast(null,className);
    }

    /**
     * Gets last table entry
     * @param filter Model filter
     * @param className Model class
     * @return Last table entity entry
     */
    public T GetLast(U filter,Class<T> className) {
        try {
            RestApiEx<T,U> restApi = new RestApiEx<>();
            //      String gg = SEARCH_URL + "/" + Integer.toString(id);
            HttpResponse<JsonNode> response = restApi.SendPostAsync(BASE_URL + GET_LAST_URL, filter).get();

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
     * Gets first table entry
     *
     * @param className Model class
     * @return First table entity entry
     */
    public T GetFirst(Class<T> className) {
       return GetFirst(null,className);

    }

    /**
     * Gets first table entry
     * @param filter Model filter
     * @param className Model class
     * @return First table entity entry
     */
    public T GetFirst(U filter,Class<T> className) {
        try {
            RestApiEx<T,U> restApi = new RestApiEx<>();
            //      String gg = SEARCH_URL + "/" + Integer.toString(id);
            HttpResponse<JsonNode> response = restApi.SendPostAsync(BASE_URL + GET_FIRST_URL, filter).get();

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
     * Update table entry
     *
     * @param id    Object id
     * @param model Entry model
     * @return Operation success
     */
    public boolean Update(int id, T model) {
        try {
            RestApi<T> restApi = new RestApi<>();
            HttpResponse<JsonNode> response = restApi.SendPutAsync(String.format("%s%s/%d", BASE_URL, UPDATE_URL, id), model).get();
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
     * @param filter      Search filter class
     * @param entityClass Entity model
     * @return <b>List</b> which contains models
     */
    public Future<List<T>> SearchAsync(U filter, Class<? extends Entity> entityClass) {
        return service.submit(() -> {
            return this.Search(filter, entityClass);
        });
    }

    /**
     * Get object by id
     *
     * @param id     <b>Integer</b> value, which equals database <i>id</i> column
     * @param tClass Class to cast
     * @return Casted class instance
     */
    public Future<T> GetAsync(int id, Class<T> tClass) {
        return service.submit(() -> {
            return this.Get(id, tClass);
        });
    }

    /**
     * Gets last table entry
     *
     * @param className Model class
     * @return Last table entity entry
     */
    public Future<T> GetLastAsync(Class<T> className) {
        return service.submit(() -> {
            return this.GetLast(className);
        });
    }

    public Future<Boolean> AddAsync(List<T> data) {
        return service.submit(() -> {
            return this.Add(data);
        });
    }

    public void setBaseUrl(String url) {
        BASE_URL = url;
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

}
