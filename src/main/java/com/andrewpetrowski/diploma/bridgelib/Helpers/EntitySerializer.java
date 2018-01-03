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

package com.andrewpetrowski.diploma.bridgelib.Helpers;

import com.andrewpetrowski.diploma.bridgelib.Models.Entity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Helps to selrialize Entity object to JSON file
 * @param <T>
 */
public class EntitySerializer<T extends Entity> {

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
     * Convert <b>List<T></b> to JSON file
     * @param data List of objects
     * @param path File path
     * @return
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
     * Read data from JSON file to <b>List<T></b>
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
     * Read data from JSON file to <b>List<T></b>
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
     * Read data from JSON file to <b>List<T></b>
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
     * Convert <b>List<T></b> to JSON file
     * @param data List of objects
     * @param path File path
     * @return
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

    public Future<List<T>> FromJsonFileToListAsync(String path,Type type) {
        return service.submit(() -> {
            return this.FromJsonFileToList(path,type);
        });
    }


}