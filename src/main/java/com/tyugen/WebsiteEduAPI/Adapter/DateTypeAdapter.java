package com.tyugen.WebsiteEduAPI.Adapter;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 * DateTypeAdapter is a class that converts a JSON string to a Date object and vice versa.
 */
public class DateTypeAdapter extends TypeAdapter<Date> {
    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Writes a Date object to a JSON string.
     *
     * @param out   the JsonWriter object to be used
     * @param value the Date object to be converted
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void write(JsonWriter out, java.sql.Date value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            String dateString = dateFormat.format(value);
            out.value(dateString);
        }
    }

    /**
     * Reads a JSON string and converts it to a Date object.
     *
     * @param in the JsonReader object to be used
     * @return a Date object
     * @throws IOException if an I/O error occurs
     */
    @Override
    public Date read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        } else {
            String dateString = in.nextString();
            try {
                java.util.Date utilDate = dateFormat.parse(dateString);
                return new Date(utilDate.getTime());

            } catch (ParseException e) {
                throw new JsonSyntaxException(dateString, e);
            }
        }
    }
}
