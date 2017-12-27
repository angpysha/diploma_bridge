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

package com.andrewpetrowski.diploma.bridgelib.Models;

import com.andrewpetrowski.diploma.bridgelib.HttpHelpers.DateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.Optional;

/**
 * This model is using for filtering and searching data in server
 * @author Andrew Petrowsky
 * @version 1.0
 */
public class DhtSearch extends SearchEntity{

    /**
     * Begin temperature property for range search
     */
    private Float beginTemperature;

    /**
     *Get begin temperature property
     * @return Begin temperature
     */
    @JsonProperty(value = "beginTemperature")
    public Float getBeginTemperature() {
        return beginHumidity;
    }

    /**
     * Set begin temperature property
     * @param beginTemperature Begin temperature
     */
    public void setBeginTemperature(Float beginTemperature) {
        this.beginTemperature = beginTemperature;
    }

    /**
     * End temperature property for range search
     */
    private Float endTemperature;

    /**
     * Set end temperature property
     * @param endTemperature End temperature
     */
    public void setEndTemperature(Float endTemperature) {
        this.endTemperature = endTemperature;
    }

    /**
     *Get end temperature property
     * @return End temperature
     */
    @JsonProperty(value = "endTemperature")
    public Float getEndTemperature() {
        return endTemperature;
    }

    /**
     * Begin humidity property for range search
     */
    private Float beginHumidity;

    /**
     *Get begin humidity property
     * @return Begin humidity
     */
    @JsonProperty(value = "beginHumidity")
    public Float getBeginHumidity() {
        return beginHumidity;
    }

    /**
     * Set begin humidity property
     * @param beginHumidity Begin humidity
     */
    public void setBeginHumidity(Float beginHumidity) {
        this.beginHumidity = beginHumidity;
    }

    /**
     * End humidity property for range search
     */
    private Float endHumidity;

    /**
     *Get end humidity property
     * @return End humidity
     */
    @JsonProperty(value = "endHumidity")
    public Float getEndHumidity() {
        return endHumidity;
    }

    /**
     * Set end humidity property
     * @param endHumidity End humidity
     */
    public void setEndHumidity(Float endHumidity) {
        this.endHumidity = endHumidity;
    }


    /**
     * Humidity property for search
     */
    private Float Humidity;

    /**
     *Get humidity property
     * @return Humidity
     */
    @JsonProperty(value = "Humidity")
    public Float getHumidity() {
        return Humidity;
    }

    /**
     * Set humidity property
     * @param humidity Humidity
     */
    public void setHumidity(Float humidity) {
        Humidity = humidity;
    }

    /**
     * Temperature property for search
     */
    private Float Temperature;

    /**
     * Set humidity property
     * @param temperature Humidity
     */
    public void setTemperature(Float temperature) {
        Temperature = temperature;
    }

    /**
     *Get temperature property
     * @return Temperature
     */
    @JsonProperty(value = "Temperature")
    public Float getTemperature() {
        return Temperature;
    }


    /**
     * Default constructor
     */
    public DhtSearch() {

    }

    /**
     * Base constructor
     * @param beginDate Begin date
     * @param endDate End date
     * @param beginTemperature Begin temperature
     * @param endTemperature End temperature
     * @param beginHumidity Begin humidity
     * @param endHumidity End humidity
     * @param Temperature Temperature
     * @param Humidity Humidity
     */
    public DhtSearch(Optional<Date> beginDate,Optional<Date> endDate,Optional<Float> beginTemperature,
                     Optional<Float> endTemperature,Optional<Float> beginHumidity,Optional<Float> endHumidity,
                     Optional<Float> Temperature,Optional<Float> Humidity) {
        if (beginDate.isPresent())
            this.beginDate = beginDate.get();
        if (endDate.isPresent())
            this.endDate = endDate.get();
        if (beginTemperature.isPresent())
            this.beginTemperature = beginTemperature.get();
        if (endTemperature.isPresent())
            this.endTemperature = endTemperature.get();
        if (beginHumidity.isPresent())
            this.beginHumidity = beginHumidity.get();
        if (endHumidity.isPresent())
            this.endHumidity = endHumidity.get();
        if (Humidity.isPresent())
            this.Humidity = Humidity.get();
        if (Temperature.isPresent())
            this.Temperature = Temperature.get();

    }

}
