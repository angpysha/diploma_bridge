package com.andrewpetrowski.diploma.bridgelib.Controllers;

import com.andrewpetrowski.diploma.bridgelib.Models.DHT11_Data;
import com.andrewpetrowski.diploma.bridgelib.Models.DhtSearch;

public class DhtController extends BaseController<DHT11_Data,DhtSearch> {


    public DhtController() {
        SEARCH_URL = "/dhts/search";
        GET_URL =  "/dhts/get";
        ADD_URL = "/dhts/add";
    }


}
