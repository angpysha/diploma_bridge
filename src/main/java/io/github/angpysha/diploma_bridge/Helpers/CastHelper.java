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

package io.github.angpysha.diploma_bridge.Helpers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * This class can cast objects to simplify code
 *
 * @author Andrew Petrowsky
 * @version 1.0
 */
public class CastHelper {
    /**
     * Converts from LocalDate to Date
     * @param date Date in LocalDate object
     * @return  Date object, which equals LocalDate argument
     */
    public static Date LocalDateToDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
