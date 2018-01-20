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

package io.github.angpysha.diploma_bridge.Decorators;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Decorator for date for advanced functionality
 *
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
     *
     * @param date Date object
     */
    public DateEx(Date date) {
        this.dateEx = date;
    }

    /**
     * Set time to 00:00:00
     *
     * @return Date obejct with this time
     */
    public Date ZeroTime() {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(dateEx);

        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
    }

    /**
     * Add days to date
     *
     * @param days Days value
     * @return New date object
     */
    public Date AddDate(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateEx);

        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * Get date before
     *
     * @return Before date object
     */
    public Date Decrement() {
        return AddDate(-1);
    }

    /**
     * Get date after
     *
     * @return After date object
     */
    public Date Increment() {
        return AddDate(1);
    }

    /**
     * Convert {@link Date} to {@link LocalDate}
     * @return LocalDate object
     */
    public LocalDate ToLocalDate() {
        LocalDate date = dateEx.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return date;

    }

    /**
     * Converts {@link Date} to locale formatted string
     * @param locale {@link Locale} object
     * @return Localized {@link Date} string with "dd MMMM yyyy" pattern
     */
    public String toFormatedString(Locale locale) {
        LocalDate date = dateEx.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);

        return date.format(formatter);
    }

    /**
     * Converts {@link Date} to locale formatted string
     * @param date {@link Date} object
     * @param locale {@link Locale} object
     * @return Localized {@link Date} string with "dd MMMM yyyy" pattern
     */
    public static String ToFormatedLocalDateEx(Date date, Locale locale) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);

        return localDate.format(formatter);
    }

    /**
     * Get date object
     *
     * @return Date object
     */
    public Date getDateEx() {
        return dateEx;
    }

    /**
     * Set date object
     *
     * @param dateEx Date object
     */
    public void setDateEx(Date dateEx) {
        this.dateEx = dateEx;
    }
}
