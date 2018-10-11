import retrofit2.*;
import retrofit2.http.*;

import java.util.List;

public interface ExpoPushService {

    @POST("push/send")
    Call<PushTicketResponse> sendNotifications(@Body List<Message> messages);

    @POST("push/getReceipts")
    Call<List<Object>> getReceipts(@Body PushReceiptRequest request); /// TODO Model for body with ids, response

}
