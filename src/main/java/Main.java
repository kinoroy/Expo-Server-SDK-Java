import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*String BASE_URL = "https://5gla4b9oy2.execute-api.ca-central-1.amazonaws.com/Test/"; // "https://exp.host/--/api/v2/"";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExpoPushService service = retrofit.create(ExpoPushService.class);

        List<Message> list = new ArrayList<Message>();

        Call<PushTicketResponse> call = service.sendNotifications(list);

        try {
            Response<PushTicketResponse> res = call.execute();
            PushTicketResponse body = res.body();
            for (PushTicket ticket : body.getTickets()) {
                System.out.println("Ticket with id:" + ticket.getId() + " status: " + ticket.getStatus());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/

    }

}
