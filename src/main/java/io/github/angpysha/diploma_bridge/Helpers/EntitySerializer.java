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

package io.github.angpysha.diploma_bridge.Helpers;

import io.github.angpysha.diploma_bridge.Annotations.FieldProperty;
import io.github.angpysha.diploma_bridge.Models.Entity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.angpysha.diploma_bridge.Requests.ApiRequest;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Helps to selrialize Entity object to JSON file
 * @param <T> Entity class object
 */
public class EntitySerializer<T> {

    private Gson gson;
    private ObjectMapper mapper;

    private ExecutorService service = Executors.newSingleThreadExecutor();

    /**
     * Creates class instance
     */
    public EntitySerializer() {
        gson = new Gson();
    }


    /**
     * Convert json to object
     * @param json JSON object
     * @param javaClass Object class
     * @return Java object
     */
    public T JsonToObject(String json,Class<T> javaClass) {
      //  Type objectType = new TypeToken<T>(){}.getType();

        T obj = gson.fromJson(json,javaClass);
        return obj;
    }

    /**
     * Convert object to json
     * @param object Java object
     * @return Json object
     */
    public String ObjectToJson(T object) {
        String json = gson.toJson(object);
        return json;
    }
    /**
     * Convert <b>List</b> to JSON file
     * @param data List of objects
     * @param path File path
     * @return Operation result
     */
    public Boolean ListToJsonFile(List<T> data, String path) {
        Type listType = new TypeToken<List<T>>() {
        }.getType();

        try (Writer writer = new FileWriter(path)) {
            gson.toJson(data, listType, writer);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Read data from JSON file to <b>List</b>
     * @param path File path
     * @param tClass Java class
     * @return List of objects
     */
    public List<T> FromJsonFileToList(String path,Class<T[]> tClass) {
//        Type listType = new TypeToken<List<T>>() {
//        }.getType();

        try (Reader reader = new FileReader(path)) {
            T[] data = gson.fromJson(reader, tClass);
            return Arrays.asList(data);
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     *  This method converts class to map obejct using reflection
     * @param object Object to convert
     * @param javaClass Obejct class
     * @return Map with annotated properties
     */
    public Map<String,Object> ClassToFields(T object,Class<T> javaClass) {
        Map<String,Object> map = new HashMap<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(FieldProperty.class)) {
                try {
                    FieldProperty fieldProperty = field.getAnnotation(FieldProperty.class);
                    String name = field.getName();
 //                   Object val = field.get(field.getName());
                    Method vall = object.getClass().getMethod("get"+name);
                    Object valll = vall.invoke(object);
                    String prop = fieldProperty.value();
                    map.put(prop,valll);
                } catch (IllegalAccessException e) {
                    //
                    int i =0;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return map;
    }

    /**
     * Read data from JSON file to <b>List</b>
     * @param path File path
     * @param type Type
     * @return List of objects
     */
    public List<T> FromJsonFileToList(String path,Type type) {
        try (Reader reader = new FileReader(path)) {
            List<T> data = gson.fromJson(reader, type);
            return data;
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Write object to JSON file
     * @param data Data object
     * @param path File path
     * @return Operation result
     */
    public Boolean EntityToJsonFile(T data, String path) {
        Type objectType = new TypeToken<T>(){}.getType();

        try (Writer writer = new FileWriter(path)) {
            gson.toJson(data,objectType,writer);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Read data from JSON file and convert them to object
     * @param path File path
     * @param tClass Java class
     * @return Data object
     */
    public T JsonFileToEntity(String path,Class<T> tClass) {

        try (Reader reader = new FileReader(path)) {
            return gson.fromJson(reader,tClass);
        } catch (IOException ex)
        {
            return null;
        }
    }

    /**
     * Read data from JSON file to <b>List</b>
     * @param path File path
     * @param tClass Java class
     * @return List of objects
     */
    public Future<List<T>> FromJsonFileToListAsync(String path,Class<T[]> tClass) {

        return service.submit(() -> {
            return this.FromJsonFileToList(path,tClass);
        });
    }

    /**
     * Convert <b>List</b> to JSON file
     * @param data List of objects
     * @param path File path
     * @return Operation result
     */
    public Future<Boolean> ListToJsonFileAsync(List<T> data, String path) {
        return service.submit(() -> {
            return this.ListToJsonFile(data,path);
        });
    }

    /**
     * Write object to JSON file
     * @param data Data object
     * @param path File path
     * @return Operation result
     */
    public Future<Boolean> EntityToJsonFileAsync(T data, String path) {
        return service.submit(()->{
           return this.EntityToJsonFile(data,path);
        });
    }

    /**
     * Read data from JSON file and convert them to object
     * @param path File path
     * @param tClass Java class
     * @return Data object
     */
    public Future<T> JsonFileToEntityAsync(String path,Class<T> tClass) {
        return service.submit(() -> {

            return this.JsonFileToEntity(path,tClass);
        });
    }

    /**
     * Read data from JSON file to <b>List</b>
     * @param path File path
     * @param type Java type
     * @return List of objects <br> <br>
     * <b>Example</b> <br> <br>
     * {@code
     * List<DHT11_Data> data = serializer.FromJsonFileToListAsync(path,(new TypeToken<List<DHT11_Data>>(){}).getType())}
     */
    public Future<List<T>> FromJsonFileToListAsync(String path,Type type) {
        return service.submit(() -> {
            return this.FromJsonFileToList(path,type);
        });
    }


}
