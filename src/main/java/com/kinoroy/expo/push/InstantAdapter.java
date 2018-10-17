package com.kinoroy.expo.push;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

class InstantAdapter extends TypeAdapter<Instant> {

    @Override
    public void write(JsonWriter out, Instant value) throws IOException {
        if (value == null)
            out.nullValue();
        else
            out.value(value.getEpochSecond());
    }

    @Override
    public Instant read(JsonReader in) throws IOException {
        if (in != null)
            return Instant.ofEpochSecond(in.nextLong());
        else
            return null;
    }

}
