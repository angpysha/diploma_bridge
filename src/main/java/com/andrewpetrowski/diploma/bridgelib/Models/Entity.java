/*
 *    Copyright 2017 Andrew Petrowsky
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


import com.andrewpetrowski.diploma.bridgelib.HttpHelpers.DateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Base class for model
 *
 * @author Andrew Petrowsky
 * @version 1.0
 */
public abstract class Entity {
    /**
     * Object's database id
     */
    protected int id;

    /**
     * Object's creating date
     */
    @JsonProperty(value = "Created_at")
    @JsonSerialize(using = DateSerializer.class)
    protected Date Created_at;

    /**
     * Object's updating date
     */
    @JsonProperty(value = "Updated_at")
    @JsonSerialize(using = DateSerializer.class)
    protected Date Updated_at;


    protected Entity() {
    }

    /**
     * Get entity id
     *
     * @return Entity id
     */
    @JsonProperty(value = "id")
    public int getId() {
        return id;
    }

    /**
     * Set entity id
     *
     * @param id Entity id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get creation date
     *
     * @return Creation date
     */

    public Date getCreated_at() {
        return Created_at;
    }

    /**
     * Set creation date
     *
     * @param created_at Creation date
     */
    public void setCreated_at(Date created_at) {
        Created_at = created_at;
    }

    /**
     * Set update date
     * @param updated_at Update date
     */

    public void setUpdated_at(Date updated_at) {
        Updated_at = updated_at;
    }

    /**
     * Get update date
     * @return Update date
     */
    public Date getUpdated_at() {
        return Updated_at;
    }
}
