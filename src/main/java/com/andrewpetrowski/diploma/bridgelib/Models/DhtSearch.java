package com.andrewpetrowski.diploma.bridgelib.Models;

import java.util.Date;
import java.util.Optional;

/**
 * This model is using for filtering and searching data in server
 * @author Andrew Petrowsky
 * @version 1.0
 */
public class DhtSearch {
    public Optional<Date> beginDate;
    public Optional<Date> endDate;
    public Optional<Float> beginTemperature;
    public Optional<Float> endTemperature;
    public Optional<Float> beginHumidity;
    public Optional<Float> endHumidity;
    public Optional<Float> Humidity;
    public Optional<Float> Temperature;
    public Optional<Date> Date;

    public DhtSearch() {

    }

}
