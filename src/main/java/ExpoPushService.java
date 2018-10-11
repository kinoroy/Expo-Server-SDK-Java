import retrofit2.*;
import retrofit2.http.*;

import java.util.List;

public interface ExpoPushService {

    @POST("push/send")
    Call<List<Object>> sendNotifications(@Body List<Message> messages);



}
