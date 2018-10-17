package com.kinoroy.expo.push;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class ExpoPushClient {

    private static final Pattern TOKEN_PATTERN = Pattern.compile("^[a-z\\d]{8}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{12}$");
    private static final String BASE_URL = "https://exp.host/--/api/v2/";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new InstantAdapter())
            .registerTypeAdapter(Duration.class, new DurationAdapter()).create();
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    private static final ExpoPushService service = retrofit
            .create(ExpoPushService.class);

    private ExpoPushClient() {}

    /**
     * Determines if a given string appears to be a valid Expo push token
     * This only checks the structure of the string and does not verify with Expo's servers
     * whether or not the token is registered/revoked/etc.
     * @param token
     */
    public static boolean isExpoPushToken(String token) {
        boolean valid = (token.startsWith("ExponentPushToken[") ||
                token.startsWith("ExpoPushToken[")) &&
                token.endsWith("]") ||
                TOKEN_PATTERN.matcher(token).find();
        return valid;
    }

    /**
     * Sends a list of messages to Expo to be forwarded to APNS or Google Cloud Messaging.
     * Attempting to send more than 100 messages at once will result in
     * an IllegalArgumentException. Use ExpoPushClient.chunkItems(messages) to create
     * lists of acceptable size.
     * <p>
     * It's recommended to check whether your push tokens are syntactically valid before
     * sending by using <code>ExpoPushClient.isExpoPushToken(token)</code> on each token.
     * @param messages A list of up to 100 message objects to be sent to Expo.
     * @return  The response from Expo's server with a list of PushTickets and a list of PushErrors if any
     * @throws IOException If there was an error with sending the HTTP POST request to Expo's servers
     */
    public static PushTicketResponse sendPushNotifications(List<Message> messages) throws IOException {
        if(messages.size() > 100) {
            throw new IllegalArgumentException("More than 100 messages cannot be sent at once." +
                    " Use ExpoPushClient.chunkItems(messages)");
        }
        Call<PushTicketResponse> call = service.sendNotifications(messages);
        Response<PushTicketResponse> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            String eBody = response.errorBody().string();
            PushTicketResponse eResponse = new GsonBuilder().create().fromJson(eBody, PushTicketResponse.class);
            return response.body();
        }
    }

    /**
     * Retrieves push receipts from Expo.
     * <p>
     * Once Expo delivers a notification to the iOS or Android push notification service, Expo creates a push receipt
     * @param ids A list of ids retrieved from PushTickets.
     *            There must not be more than 100 items in this list,
     *            otherwise IllegalArgumentException will be thrown
     * @return The response from Expo's servers with a map of PushReceipts
     * @throws IOException If there was an error with sending the HTTP POST request to Expo's servers
     */
    public static PushReceiptResponse getPushReciepts(List<String> ids) throws IOException {
        if(ids.size() > 100) {
            throw new IllegalArgumentException("More than 100 receipts cannot be retrieved at once." +
                    " Use ExpoPushClient.chunkItems(ids)");
        }
        PushReceiptRequest request = new PushReceiptRequest();
        request.setIds(ids);
        Call<PushReceiptResponse> call = service.getReceipts(request);
        Response<PushReceiptResponse> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            String eBody = response.errorBody().string();
            PushReceiptResponse eResponse = new GsonBuilder().create().fromJson(eBody, PushReceiptResponse.class);
            return eResponse;
        }
    }

    /**
     * Takes a list of items and returns a list of lists, where each list is no more than 100 items in size.
     * This is a requirement of Expo's servers.
     * @param items A list of items to chunk
     * @param <T> The type of the item. Either Message for messages or String for ids.
     * @return A list of lists, each of which is no more than 100 items in size.
     */
    public static <T> List<List<T>> chunkItems(List<T> items) {
        List<List<T>> chunks = new ArrayList<>();
        int numChunks = (int)Math.ceil(items.size() / 100);
        for (int i = 0; i < numChunks; i++) {
            List<T> chunk = items.subList(i*100, (i+1)*100);
            chunks.add(chunk);
        }
        return chunks;
    }
}
