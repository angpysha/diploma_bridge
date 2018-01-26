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

/**
 * Model which store DHT11 sensor data (humidity and temperature)
 * @author Andrew Petrowsky
 * @version 1.0
 */
public class DHT11_Data extends Entity {

    /**
     * This property shows temperature from sensor
     */
    private float Temperature;

    /**
     * This property shows humidity from sensor
     */
    private float Humidity;

    /**
     * Override <b>toString()</b> operator.
     * @return  A string, which looks like: <i>"Temperature: %d; Humidity: %d"</i>
     */
    @Override
    public String toString()
    {
        return String.format("Temperature: %d; Humidity: %d",this.Temperature,this.Humidity);
    }


    /**
     * Default constructor for creating DHT11 sensor data
     * @param data array of 5 elements, which contains raw sensor data
     */
    public DHT11_Data(int[] data) {
        this.Humidity = (float) ((data[0] << 8) + data[1]) / 10;
        this.Temperature = (float) (((data[2] & 0x7F) << 8) + data[3]) / 10;
        this.Created_at = new Date();
        this.Updated_at = new Date();

    }

    public DHT11_Data(float temperature,float humidity) {
        this.Temperature = temperature;
        this.Humidity = humidity;
        this.Created_at = new Date();
        this.Updated_at = new Date();
    }

    public DHT11_Data(Date created,Date updated,
                      Float temperature,Float humidity){
        Created_at = created;
        Updated_at = updated;
        this.Temperature = temperature;
        this.Humidity = humidity;
    }
    /**
     * Dummy constructor for DHT11 sensor data
     */
    public DHT11_Data() {
        this.Created_at = new Date();
        this.Updated_at = new Date();
    }

    /**
     * Get temperature from sensor
     * @return Temperature from sensor at Celsius degree
     */
    @JsonProperty(value = "Temperature")
    public float getTemperature() {
        return Temperature;
    }

    /**
     * Set temperature from sensor
     * @param temperature Temperature from sensor at Celsius degree
     */
    public void setTemperature(float temperature) {
        Temperature = temperature;
    }

    /**
     * Get humidity from sensor
     * @return Humidity from sensor
     */
    @JsonProperty(value = "Humidity")
    public float getHumidity() {
        return Humidity;
    }

    /**
     * Set humidity from sensor
     * @param humidity Humidity from sensor
     */
    public void setHumidity(float humidity) {
        Humidity = humidity;
    }
}
