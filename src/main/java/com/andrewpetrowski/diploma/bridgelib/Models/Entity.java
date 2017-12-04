package com.andrewpetrowski.diploma.bridgelib.Models;


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
    protected Date Created_at;

    /**
     * Object's updating date
     */
    protected Date Updated_at;


    protected Entity() {
    }

    /**
     * Get entity id
     *
     * @return Entity id
     */
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
