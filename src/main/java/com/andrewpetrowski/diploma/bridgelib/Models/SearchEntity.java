package com.andrewpetrowski.diploma.bridgelib.Models;

import com.andrewpetrowski.diploma.bridgelib.HttpHelpers.DateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * A base class for search
 */
public abstract class SearchEntity {
    /**
     * Begin date property for range search
     */
    @JsonSerialize(using = DateSerializer.class)
    protected Date beginDate;

    /**
     * Get filter begin date
     * @return Filter begin date
     */
    @JsonProperty(value = "beginDate")
    @JsonSerialize(using = DateSerializer.class)
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * Set filter begin date
     * @param beginDate Filter begin date
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * End date property for range search
     */
    @JsonSerialize(using = DateSerializer.class)
    protected Date endDate;

    /**
     * Get filter end date
     * @return Filter end date
     */
    @JsonProperty(value = "endDate")
    @JsonSerialize(using = DateSerializer.class)
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set filter end date
     * @param endDate Filter end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
