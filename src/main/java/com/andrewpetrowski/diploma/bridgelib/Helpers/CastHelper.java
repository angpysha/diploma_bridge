package com.andrewpetrowski.diploma.bridgelib.Helpers;

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
