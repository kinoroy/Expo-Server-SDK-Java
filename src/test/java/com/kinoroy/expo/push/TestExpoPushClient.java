package com.kinoroy.expo.push;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TestExpoPushClient {

    @Test
    public void testChunkItems() {

        List<Message> messageList = new ArrayList<>();

        for (int i = 0; i < 125; i++) {
            messageList.add(new Message.Builder()
            .title("h").to("sdfsdf").body("sdfsd").build());
        }

        List<List<Message>> listOfLists = ExpoPushClient.chunkItems(messageList);

        assertEquals(2, listOfLists.size());
        assertEquals(100, listOfLists.get(0).size());
        assertEquals(25, listOfLists.get(1).size());

    }

}
