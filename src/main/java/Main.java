import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //List<String> tokens = new ArrayList<>();
        //tokens.add("ExponentPushToken[AWFrgUGvR_Ws4_RpVIy_Mu]");
        /*List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setTo("ExponentPushToken[AWFrgUGvR_Ws4_RpVIy_Mu]");
        message.setTitle("You've got mail!");
        message.setBody("Check your messages");
        messages.add(message);
        try {
            PushTicketResponse response = ExpoPushClient.sendPushNotifications(messages);
            List<ExpoError> errors = response.getErrors();
            if (errors != null && errors.size() > 0) {
                System.out.println(errors.get(0).getCode());
                System.out.println(errors.get(0).getMessage());
            }
            List<PushTicket> tickets = response.getTickets();
            if (tickets != null && tickets.size() > 0) {
                System.out.println(tickets.get(0).getStatus());
                System.out.println(tickets.get(0).getDetails());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }*/
        // df47cdfa-8404-41a1-a2bb-677c400d920f
        List<String> ids = new ArrayList<>();
        ids.add("df47cdfa-8404-41a1-a2bb-677c400d920f");
        try {
            PushReceiptResponse response = ExpoPushClient.getPushReciepts(ids);
            PushReceipt rec = response.getReceipts().get("df47cdfa-8404-41a1-a2bb-677c400d920f");
            System.out.println(rec.getDetails());
            System.out.println(rec.getStatus());
            System.out.println(rec.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
