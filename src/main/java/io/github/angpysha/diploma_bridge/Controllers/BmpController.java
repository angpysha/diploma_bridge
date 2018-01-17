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

package com.andrewpetrowski.diploma.bridgelib.Controllers;

import com.andrewpetrowski.diploma.bridgelib.Models.Bmp180_Data;
import com.andrewpetrowski.diploma.bridgelib.Models.BmpSearch;

public class BmpController extends BaseController<Bmp180_Data,BmpSearch>{
    /**
     * Dummy class constructor
     */
    public BmpController() {
        SEARCH_URL = "/bmps/search";
        GET_URL =  "/bmps/get";
        ADD_URL = "/bmps/add";
        UPDATE_URL="/bmps/update";
        DELETE_URL="/bmps/delete";
        GET_LAST_URL  = "/bmps/last";
        GET_SIZE_URL = "/bmps/datecount";
    }
}
