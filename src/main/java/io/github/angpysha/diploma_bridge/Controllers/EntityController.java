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

import com.google.gson.reflect.TypeToken;
import io.github.angpysha.diploma_bridge.Decorators.DateEx;
import io.github.angpysha.diploma_bridge.HttpClient.ERequestType;
import io.github.angpysha.diploma_bridge.HttpClient.HttpClient;
import io.github.angpysha.diploma_bridge.Models.DisplayPeriod;
import io.github.angpysha.diploma_bridge.Models.Entity;
import io.github.angpysha.diploma_bridge.Models.SearchEntity;
import org.joda.time.DateTime;
import org.json.JSONObject;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public abstract class EntityController<T extends Entity, U extends SearchEntity> implements IEntityController {

    private Class<T> returnClass;
    private Class<U> filterClass;

    public String BASEURL = "";

    protected SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    protected EntityController(Class<T> returnClass,Class<U> filterClassClass) {
        this.returnClass = returnClass;

        this.filterClass = filterClassClass;
    }

    private  Integer apply(String x) {
        final JSONObject jsonObject = new JSONObject(x);

        String vaa = jsonObject.get("pages").toString();

        return Integer.parseInt(vaa);
    }

    public CompletableFuture<T> Get(int id,String postfix) {
        try {
            String url = BASEURL + postfix;
            Map<String, String> map = new HashMap<>();
            return HttpClient.SendRequestAsync(url, returnClass, ERequestType.POST, null, map);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public CompletableFuture<List<T>> Search(Object searchFilter, String postfix) {
        try {

            String url = BASEURL + postfix;
            Function<String,List<T>> function = this::ConvertToList;
            Map<String, String> map = new HashMap<>();
            CompletableFuture<List<T>> json =  HttpClient.SendRequestAsyncJson(url,ERequestType.POST,searchFilter,map)
                    .thenApply(this::ConvertToList);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public CompletableFuture<Integer> GetDatesCount(String postfix) {
        try {
            String url = BASEURL + postfix;

            Map<String, String> map = new HashMap<>();
            return HttpClient.SendRequestAsyncJson(url,ERequestType.POST,null,map)
                    .thenApply(this::apply);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public CompletableFuture<Boolean> Add(Object data,String postfix) {
        try {
            String url = BASEURL + postfix;

            Map<String, String> map = new HashMap<>();

            return HttpClient.SendRequestAsyncJson(url,ERequestType.POST,data,map)
                    .thenApply(this::ConvertToBoolean);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public CompletableFuture<Integer> GetCount(T model) {
        try {
            DateTime dateTime = DateTime.now();

            dateTime = dateTime.plusSeconds(-1);

            Date date1 = dateTime.toDate();

            DateTime dateTime1 = DateTime.now();

            dateTime1 = dateTime1.plusSeconds(1);

            Date date2 = dateTime1.toDate();

            U search = filterClass.getDeclaredConstructor().newInstance();

            search.setBeginDate(date1);
            search.setEndDate(date2);

            return Search(search,"/search").thenApply( x -> x.size());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public CompletableFuture<List<T>> GetByPeriod(Date date, DisplayPeriod period) {
        Class[] args = new Class[2];
        args[0] = Date.class;
        args[1] = Date.class;
        Date begin = new DateEx(date).ZeroTime();
        switch (period) {
            case DAY: {
                Date after = new DateEx(begin).Increment();
                try {
                    U filter = filterClass.getDeclaredConstructor(args).newInstance(begin, after);
                    CompletableFuture<List<T>> data = Search(filter, "");
                    return data;
                } catch (InstantiationException |
                        NoSuchMethodException |
                        IllegalAccessException | InvocationTargetException e) {
                    return null;
                }
            }
            case WEEK: {
                Date tmp = begin;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                CompletableFuture<List<T>> data;
                int dayOfWeek = DateEx.GetLocalWeekDay(calendar.get(Calendar.DAY_OF_WEEK),
                        calendar.getFirstDayOfWeek());
                data = FilterData(dayOfWeek,tmp,returnClass,filterClass, args);
                return data;
            }
            case MONTH: {
                Date tmp = begin;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                CompletableFuture<List<T>> data;
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                data = FilterData(dayOfMonth, tmp,returnClass,filterClass, args);
                return data;
            }
            case YEAR: {
                Date tmp = begin;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                CompletableFuture<List<T>> data;
                int dayOfMonth = calendar.get(Calendar.MONTH);
                data = FilterData(dayOfMonth,tmp,returnClass,filterClass, args);
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
    private CompletableFuture<List<T>> FilterData(int numElems, Date date, Class<T> tClass, Class<U> uClass, Class[] args) {
        List<T> data = new ArrayList<>();
        CompletableFuture<List<T>> elems = new CompletableFuture<>();
        for (int i = 0; i < numElems; i++) {
            Date after = new DateEx(date).Increment();
            try {
                U filter = uClass.getDeclaredConstructor(args).newInstance(date, after);
                List<T> dat = Search(filter, "").get();
                T aver = GetAverage(dat,i);
                data.add(aver);
                date = new DateEx(date).Decrement();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | InterruptedException | ExecutionException e) {
//                        e.printStackTrace();
            }
        }

        elems.complete(data);
        return elems;
    }

    /**
     * Get average value of List (must be {@code List<? extends Entity>})
     * @param data List, needed to get average
     * @param pos Page position
     * @return Average value
     */
    public abstract T GetAverage(List<T> data,int pos);

    private List<T> ConvertToList(String s) {
        List<T> items = HttpClient.gson.fromJson(s,new TypeToken<List<T>>(){}.getType());
        return items;
    }

    private Boolean ConvertToBoolean(String s) {
        final JSONObject jsonObject = new JSONObject(s);

        String val = jsonObject.get("result").toString();

        return Boolean.parseBoolean(val);
    }







}
