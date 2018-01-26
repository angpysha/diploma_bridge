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
import io.github.angpysha.diploma_bridge.Models.DHT11_Data;
import io.github.angpysha.diploma_bridge.Models.DhtSearch;

import java.util.Date;
import java.util.List;

/**
 * This class provides access to DHT11 database table at server
 * @author Andrew Petrowsky
 * @version 1.0
 */
public class DhtController extends BaseController<DHT11_Data,DhtSearch> {

    /**
     * Dummy class constructor
     */
    public DhtController() {
        SEARCH_URL = "/dhts/search";
        GET_URL =  "/dhts/get";
        ADD_URL = "/dhts/add";
        UPDATE_URL="/dhts/update";
        DELETE_URL="/dhts/delete";
        GET_LAST_URL  = "/dhts/last";
        GET_SIZE_URL = "/dhts/datecount";
    }

    @Override
    public DHT11_Data GetAverage(List<DHT11_Data> data, int pos) {
        Float humidity=0f, temperature = 0f;

        for (DHT11_Data d : data) {
            humidity += d.getHumidity();
            temperature += d.getTemperature();
        }

        humidity = humidity / data.size();
        temperature = temperature / data.size();
        Date date1 = new Date(),date2 = new Date();
        if (!data.isEmpty()) {
            date1 = new DateEx(data.get(0).getCreated_at()).ZeroTime();
            date2 = new DateEx(data.get(0).getCreated_at()).ZeroTime();
        } else {
            date1 = new DateEx(new Date()).AddDate(-pos);
            date2 = new DateEx(new Date()).AddDate(-pos);
            temperature = temperature.isNaN()?0f:temperature;
            humidity = humidity.isNaN()?0f:humidity;
        }
        return new DHT11_Data(date1,date1,temperature,humidity);
    }
}
