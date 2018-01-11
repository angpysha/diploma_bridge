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
        GET_LAST_URL  = "/dhts/last";
        GET_SIZE_URL = "/dhts/datecount";
    }


}
