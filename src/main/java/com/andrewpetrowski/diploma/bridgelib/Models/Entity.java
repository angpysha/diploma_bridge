package com.andrewpetrowski.diploma.bridgelib.Models;


import java.util.Date;

/**
 * Base class for model
 * @author Andrew Petrowsky
 * @version 1.0
 */
public abstract class Entity {
    /**
     * Object's database id
     */
    public int Id;

    /**
     * Object's creating date
     */
    public Date Created_at;

    /**
     * Object's updating date
     */
    public Date Updated_at;


}
