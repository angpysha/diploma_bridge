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

public class Bmp180_Data extends Entity {

    private Float Temperature;

    private Float Altitude;

    private Float Pressure;

    @JsonProperty(value = "Temperature")
    public Float getTemperature() {
        return Temperature;
    }

    public void setTemperature(Float temperature) {
        Temperature = temperature;
    }

    @JsonProperty(value = "Altitude")
    public Float getAltitude() {
        return Altitude;
    }

    public void setAltitude(Float altitude) {
        Altitude = altitude;
    }

    @JsonProperty(value = "Pressure")
    public Float getPressure() {
        return Pressure;
    }

    public void setPressure(Float pressure) {
        Pressure = pressure;
    }

    public Bmp180_Data(Float Temperature, Float Altitude, Float Pressure) {
        this.Created_at = new Date();
        this.Updated_at = new Date();
        this.Temperature = Temperature;
        this.Altitude = Altitude;
        this.Pressure = Pressure;
    }

    public Bmp180_Data() {
        this.Created_at = new Date();
        this.Updated_at = new Date();
    }
}
