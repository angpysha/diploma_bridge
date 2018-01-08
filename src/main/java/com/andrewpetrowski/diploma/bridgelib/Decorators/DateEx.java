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

package com.andrewpetrowski.diploma.bridgelib.Decorators;

import java.util.Calendar;
import java.util.Date;

/**
 * Decorator for date for advanced functionality
 * @author Andrew Petrowsky
 * @version 0.3
 */
public class DateEx extends Date {
    /**
     * Date object
     */
    protected Date dateEx;

    /**
     * Create class instance
     * @param date Date object
     */
    public DateEx(Date date) {
        this.dateEx = date;
    }

    /**
     * Set time to 00:00:00
     * @return Date obejct with this time
     */
    public Date ZeroTime() {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(dateEx);

        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        return calendar.getTime();
    }

    /**
     * Get date object
     * @return Date object
     */
    public Date getDateEx() {
        return dateEx;
    }

    /**
     * Set date object
     * @param dateEx Date object
     */
    public void setDateEx(Date dateEx) {
        this.dateEx = dateEx;
    }
}
