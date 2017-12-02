package com.andrewpetrowski.diploma.bridgelib.Models;

public class DHT11_Data extends Entity {

    /**
     * This property shows temperature from sensor
     */
    public float Temperature;

    /**
     * This property shows humidity from sensor
     */
    public float Humidity;

    /**
     * Default constructor for creating DHT11 sensor data
     * @param data array of 5 elements, which contains raw sensor data
     */
    public DHT11_Data(int[] data) {
        this.Humidity = (float) ((data[0] << 8) + data[1]) / 10;
        this.Temperature = (float) (((data[2] & 0x7F) << 8) + data[3]) / 10;

    }
}
