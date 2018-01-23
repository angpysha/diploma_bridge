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

import io.github.angpysha.diploma_bridge.HttpHelpers.DateSerializer;
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

    /**
     * Set basic data
     * @param beginDate Begin date
     * @param endDate End date
     */
    public SearchEntity(Date beginDate,Date endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    /**
     * Dummy constructor
     */
    public SearchEntity() {}
}
