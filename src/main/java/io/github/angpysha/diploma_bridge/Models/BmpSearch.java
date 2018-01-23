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

package io.github.angpysha.diploma_bridge.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BmpSearch extends SearchEntity {

    private Float beginTemperature;
    private Float endTemperature;
    private Float beginAltitude;
    private Float endAltitude;
    private Float beginPressure;
    private Float endPressure;

    @JsonProperty(value = "beginTemperature")
    public float getBeginTemperature() {
        return beginTemperature;
    }

    public void setBeginTemperature(float beginTemperature) {
        this.beginTemperature = beginTemperature;
    }

    @JsonProperty(value = "endTemperature")
    public float getEndTemperature() {
        return endTemperature;
    }

    public void setEndTemperature(float endTemperature) {
        this.endTemperature = endTemperature;
    }

    @JsonProperty(value = "beginAltitude")
    public float getBeginAltitude() {
        return beginAltitude;
    }

    public void setBeginAltitude(float beginAltitude) {
        this.beginAltitude = beginAltitude;
    }

    @JsonProperty(value = "endAltitude")
    public float getEndAltitude() {
        return endAltitude;
    }

    public void setEndAltitude(float endAltitude) {
        this.endAltitude = endAltitude;
    }

    @JsonProperty(value = "beginPressure")
    public float getBeginPressure() {
        return beginPressure;
    }

    public void setBeginPressure(float beginPressure) {
        this.beginPressure = beginPressure;
    }

    @JsonProperty(value = "endPressure")
    public float getEndPressure() {
        return endPressure;
    }

    public void setEndPressure(float endPressure) {
        this.endPressure = endPressure;
    }

    public BmpSearch(Date beginDate, Date endDate,
                     Float beginTemperature, Float endTemperature,
                     Float beginAltitude, Float endAltitude,
                     Float beginPressure, Float endPressure) {
        super(beginDate,endDate);
        this.beginTemperature = beginTemperature;
        this.endTemperature = endTemperature;
        this.beginAltitude = beginAltitude;
        this.endAltitude = endAltitude;
        this.beginPressure = beginPressure;
        this.endPressure = endPressure;
    }

    public BmpSearch() {}

}
