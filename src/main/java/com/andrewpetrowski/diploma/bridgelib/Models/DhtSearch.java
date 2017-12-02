package com.andrewpetrowski.diploma.bridgelib.Models;

import java.util.Date;
import java.util.Optional;

/**
 * This model is using for filtering and searching data in server
 * @author Andrew Petrowsky
 * @version 1.0
 */
public class DhtSearch {
    /**
     * Begin date property for range search
     */
    public Optional<Date> beginDate;

    /**
     * End date property for range search
     */
    public Optional<Date> endDate;

    /**
     * Begin temperature property for range search
     */
    public Optional<Float> beginTemperature;

    /**
     * End temperature property for range search
     */
    public Optional<Float> endTemperature;

    /**
     * Begin humidity property for range search
     */
    public Optional<Float> beginHumidity;

    /**
     * End humidity property for range search
     */
    public Optional<Float> endHumidity;

    /**
     * Humidity property for search
     */
    public Optional<Float> Humidity;

    /**
     * Temperature property for search
     */
    public Optional<Float> Temperature;

    /**
     * Date property for search
     */
    public Optional<Date> Date;

    /**
     * Default constructor
     */
    public DhtSearch() {

    }

}
