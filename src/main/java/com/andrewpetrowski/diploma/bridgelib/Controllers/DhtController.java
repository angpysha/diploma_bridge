package com.andrewpetrowski.diploma.bridgelib.Controllers;

import com.andrewpetrowski.diploma.bridgelib.Models.DHT11_Data;
import com.andrewpetrowski.diploma.bridgelib.Models.DhtSearch;

/**
 * This class provides access to DHT11 database table at server
 * @author Andrew Petrowsky
 * @version 1.0
 */
public class DhtController extends BaseController<DHT11_Data,DhtSearch> {

    /**
     * Dummy class constructor
     */
    public DhtController() {
        SEARCH_URL = "/dhts/search";
        GET_URL =  "/dhts/get";
        ADD_URL = "/dhts/add";
        UPDATE_URL="/dhts/update";
        DELETE_URL="/dhts/delete";
    }


}
