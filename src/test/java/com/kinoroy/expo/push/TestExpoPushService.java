package com.kinoroy.expo.push;

import com.google.gson.GsonBuilder;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;

public class TestExpoPushService {

    private MockWebServer mockServer;
    private ExpoPushService service;

    @Before
    public void setUp() throws Exception{
        mockServer = new MockWebServer();
        mockServer.start();
        String BASE_URL = mockServer.url("/").toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ExpoPushService.class);
    }

    @Test
    public void testSendNotificationsAllSucc() throws Exception {
        String filename = "sendNotifResponseAllSucc.json";
        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setHeader("content-type", "application/json")
                .setBody(readFromFile(filename));
        mockServer.enqueue(response);
        PushTicketResponse res = service.sendNotifications(new ArrayList<Message>()).execute().body();
        assertNull(res.getErrors());

        assertTrue(res.getTickets().size() == 2);

        PushTicket t1 = res.getTickets().get(0);
        assertNotNull(t1);
        assertTrue(t1.getId().equals("XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX"));
        assertTrue(t1.getStatus().equals(Status.OK));

        PushTicket t2 = res.getTickets().get(1);
        assertNotNull(t2);
        assertTrue(t2.getId().equals("YYYYYYYY-YYYY-YYYY-YYYY-YYYYYYYYYYYY"));
        assertTrue(t2.getStatus().equals(Status.OK));
    }

    @Test
    public void testSendNotificationsSomeSucc() throws Exception {
        String filename = "sendNotifResponseSomeSucc.json";
        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setHeader("content-type", "application/json")
                .setBody(readFromFile(filename));
        mockServer.enqueue(response);
        PushTicketResponse res = service.sendNotifications(new ArrayList<Message>()).execute().body();
        assertNull(res.getErrors());

        PushTicket t1 = res.getTickets().get(0);
        assertNotNull(t1);
        assertEquals(t1.getStatus(), Status.ERROR);
        assertEquals(t1.getMessage(), "\"ExponentPushToken[xxxxxxxxxxxxxxxxxxxxxx]\" is not a registered push notification recipient");
        assertNull(t1.getId());
        assertNotNull(t1.getDetails());
        assertEquals(t1.getDetails().getError(), PushError.DEVICE_NOT_REGISTERED);

        PushTicket t2 = res.getTickets().get(1);
        assertNotNull(t2);
        assertEquals(t2.getStatus(), Status.OK);
        assertNull(t2.getMessage());
        assertNull(t2.getDetails());
        assertEquals(t2.getId(), "XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX");

    }

    @Test
    public void testSendNotificationsRequestError() throws Exception {
        String filename = "sendNotifResponseRequestError.json";

        MockResponse response = new MockResponse()
                .setResponseCode(500)
                .setHeader("content-type", "application/json")
                .setBody(readFromFile(filename));
        mockServer.enqueue(response);
        String eBody = service.sendNotifications(new ArrayList<Message>()).execute().errorBody().string();
        PushTicketResponse res = new GsonBuilder().create().fromJson(eBody, PushTicketResponse.class);

        assertNotNull(res.getErrors());
        assertEquals(res.getErrors().size(), 1);
        assertEquals(res.getErrors().get(0).getCode(), "INTERNAL_SERVER_ERROR");
        assertEquals(res.getErrors().get(0).getMessage(), "An unknown error occurred.");

    }

    @Test
    public void testGetRecResponseSomeSucc() throws Exception {
        String filename = "getRecResponseSomeSucc.json";
        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setHeader("content-type", "application/json")
                .setBody(readFromFile(filename));
        mockServer.enqueue(response);
        PushReceiptResponse res = service.getReceipts(new PushReceiptRequest()).execute().body();

        assertNotNull(res.getReceipts());
        assertEquals(res.getReceipts().size(), 2);

        PushReceipt r1 = res.getReceipts().get("XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX");
        assertNotNull(r1);
        assertEquals(r1.getStatus(), Status.ERROR);
        assertEquals(r1.getMessage(), "The Apple Push Notification service failed to send the notification");
        assertNotNull(r1.getDetails());
        assertEquals(r1.getDetails().getError(), PushError.DEVICE_NOT_REGISTERED);

        PushReceipt r2 = res.getReceipts().get("YYYYYYYY-YYYY-YYYY-YYYY-YYYYYYYYYYYY");
        assertNotNull(r2);
        assertEquals(r2.getStatus(), Status.OK);
        assertNull(r2.getMessage());
        assertNull(r2.getDetails());
    }

    @After
    public void tearDown() throws Exception {
        mockServer.shutdown();
    }

    private String readFromFile(String fileName) throws Exception {

        // Credit: https://www.journaldev.com/875/java-read-file-to-string
        URL path = TestExpoPushService.class.getResource(fileName);
        File f = new File(path.getFile());
        BufferedReader reader = new BufferedReader(new FileReader(f));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        // delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();

        return content;

    }

}