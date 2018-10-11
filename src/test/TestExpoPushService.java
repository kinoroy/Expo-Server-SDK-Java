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
        //String BASE_URL = "https://5gla4b9oy2.execute-api.ca-central-1.amazonaws.com/Test/"; // "https://exp.host/--/api/v2/"";
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