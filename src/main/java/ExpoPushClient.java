import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ExpoPushClient {

    private static final Pattern TOKEN_PATTERN = Pattern.compile("^[a-z\\d]{8}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{12}$");
    private static final String BASE_URL = "https://exp.host/--/api/v2/";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static final ExpoPushService service = retrofit
            .create(ExpoPushService.class);

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static ExpoPushService getService() {
        return service;
    }

    public static boolean isExpoPushToken(String token) {
        boolean valid = (token.startsWith("ExponentPushToken[") ||
                token.startsWith("ExpoPushToken[")) &&
                token.endsWith("]") &&
                TOKEN_PATTERN.matcher(token).find();
        return valid;
    }

    public static PushTicketResponse sendPushNotifications(List<Message> messages) throws IOException {
        if(messages.size() > 100) {
            throw new IllegalArgumentException("More than 100 messages cannot be sent at once. Use ExpoPushClient.chunkItems()");
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

    public static PushReceiptResponse getPushReciepts(List<String> ids) throws IOException {
        if(ids.size() > 100) {
            throw new IllegalArgumentException("More than 100 receipts cannot be retrieved at once. Use ExpoPushClient.chunkItems()");
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
