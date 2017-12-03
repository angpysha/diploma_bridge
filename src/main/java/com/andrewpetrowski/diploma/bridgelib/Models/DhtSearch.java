package com.andrewpetrowski.diploma.bridgelib.Models;

import java.util.Date;
import java.util.Optional;

/**
 * This model is using for filtering and searching data in server
 * @author Andrew Petrowsky
 * @version 1.0
 */
public class DhtSearch extends SearchEntity{
    /**
     * Begin date property for range search
     */
    public Date beginDate;

    /**
     * End date property for range search
     */
    public Date endDate;

    /**
     * Begin temperature property for range search
     */
    public Float beginTemperature;

    /**
     * End temperature property for range search
     */
    public Float endTemperature;

    /**
     * Begin humidity property for range search
     */
    public Float beginHumidity;

    /**
     * End humidity property for range search
     */
    public Float endHumidity;

    /**
     * Humidity property for search
     */
    public Float Humidity;

    /**
     * Temperature property for search
     */
    public Float Temperature;

    /**
     * Date property for search
     */
    public Date Date;

    /**
     * Default constructor
     */
    public DhtSearch() {

    }

}
