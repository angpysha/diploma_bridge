package com.andrewpetrowski.diploma.bridgelib.Models;

public class DHT11_Data extends Entity {
    public float Temperature;
    public float Humidity;

    public DHT11_Data(int[] data) {
        this.Humidity = (float) ((data[0] << 8) + data[1]) / 10;
        this.Temperature = (float) (((data[2] & 0x7F) << 8) + data[3]) / 10;
    }
}
