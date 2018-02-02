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
import io.github.angpysha.diploma_bridge.Models.Bmp180_Data;
import io.github.angpysha.diploma_bridge.Models.BmpSearch;

import java.util.Date;
import java.util.List;

public class BmpController extends BaseController<Bmp180_Data, BmpSearch> {
    /**
     * Dummy class constructor
     */
    public BmpController() {
        SEARCH_URL = "/bmps/search";
        GET_URL = "/bmps/get";
        ADD_URL = "/bmps/add";
        UPDATE_URL = "/bmps/update";
        DELETE_URL = "/bmps/delete";
        GET_LAST_URL = "/bmps/last";
        GET_SIZE_URL = "/bmps/datecount";
        GET_MAX_MIN_DATES_URL = "/bmps/firstlastdates";
    }

    @Override
    public Bmp180_Data GetAverage(List<Bmp180_Data> data,int pos) {
        //     return super.GetAverage(data);
        Float altitude = 0f, pressure = 0f, temperature = 0f;

        for (Bmp180_Data d : data) {
            altitude += d.getAltitude();
            pressure += d.getPressure();
            temperature += d.getTemperature();
        }

        altitude = altitude / data.size();
        pressure = pressure / data.size();
        temperature = temperature / data.size();
        Date date1 = new Date(),date2 = new Date();
        if (!data.isEmpty()) {
            date1 = new DateEx(data.get(0).getCreated_at()).ZeroTime();
            date2 = new DateEx(data.get(0).getCreated_at()).ZeroTime();
        } else {
            date1 = new DateEx(new Date()).AddDate(-pos);
            date2 = new DateEx(new Date()).AddDate(-pos);
            temperature = temperature.isNaN()?0f:temperature;
            pressure = pressure.isNaN()?0f:pressure;
            altitude = altitude.isNaN()?0f:altitude;
        }
        return new Bmp180_Data(date1,date1,temperature,altitude,pressure);
    }
}
