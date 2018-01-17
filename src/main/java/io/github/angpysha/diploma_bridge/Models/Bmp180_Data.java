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

package com.andrewpetrowski.diploma.bridgelib.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Bmp180_Data extends Entity {

    private float Temperature;

    private float Altitude;

    private float Pressure;

    @JsonProperty(value = "Temperature")
    public float getTemperature() {
        return Temperature;
    }

    public void setTemperature(float temperature) {
        Temperature = temperature;
    }

    @JsonProperty(value = "Altitude")
    public float getAltitude() {
        return Altitude;
    }

    public void setAltitude(float altitude) {
        Altitude = altitude;
    }

    @JsonProperty(value = "Pressure")
    public float getPressure() {
        return Pressure;
    }

    public void setPressure(float pressure) {
        Pressure = pressure;
    }

    public Bmp180_Data(float Temperature, float Altitude, float Pressure) {
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
