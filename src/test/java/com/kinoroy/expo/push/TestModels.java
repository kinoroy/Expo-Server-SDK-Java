package com.kinoroy.expo.push;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import static com.kinoroy.expo.push.Util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestModels {

    @Test
    public void testSerializeMessage() throws Exception {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Instant.class, new InstantAdapter())
                .registerTypeAdapter(Duration.class, new DurationAdapter()).create();

        Message message = new Message.Builder()
                .to("ExponentPushToken[xxxxxxxxxxxxxxxxxxxxxx]")
                .title("Test")
                .body("This is only a test")
                .badge(11)
                .channelId("abc")
                .priority(Priority.DEFAULT)
                .expiration(Instant.ofEpochSecond(1540145105))
                .ttl(Duration.ofDays(1))
                .sound("mySound.wav")
                .build();

        String loadedJson = readFromFile("testMessage.json");
        Message loadedMessage = gson.fromJson(loadedJson, Message.class);

        assertEquals(message.getTo(), loadedMessage.getTo());
        assertEquals(message.getTitle(), loadedMessage.getTitle());
        assertEquals(message.getBody(), loadedMessage.getBody());
        assertEquals(message.getBadge(), loadedMessage.getBadge());
        assertEquals(message.getChannelId(), loadedMessage.getChannelId());
        assertEquals(message.getPriority(), loadedMessage.getPriority());
        assertEquals(message.getExpiration(), loadedMessage.getExpiration());
        assertEquals(message.getTtl(), loadedMessage.getTtl());
        assertEquals(message.getSound(), loadedMessage.getSound());


    }


}
