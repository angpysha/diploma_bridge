package com.andrewpetrowski.diploma.bridgelib.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    }

    public DHT11_Data(float temperature,float humidity) {
        this.Temperature = temperature;
        this.Humidity = humidity;
    }

    /**
     * Dummy constructor for DHT11 sensor data
     */
    public DHT11_Data() {

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
