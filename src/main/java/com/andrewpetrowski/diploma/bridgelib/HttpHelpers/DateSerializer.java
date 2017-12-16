package com.andrewpetrowski.diploma.bridgelib.HttpHelpers;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is used for serialization date to JSON in right format
 *
 * @author Andrew Petrowsky
 * @version 1.0
 */
public class DateSerializer extends JsonSerializer<Date> {

    /**
     * Date format
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * Serialization method override
     * @param date Date, needed for serialization
     * @param gen   Json generator instance
     * @param provider  Serialization provider
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        String formattedDate = dateFormat.format(date);
        gen.writeString(formattedDate);
    }
}
