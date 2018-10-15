
# Expo Server SDK (Java)
Server side library for working with Expo using Java.
(This project is not affiliated with Expo)

### Basic Usage:

```java
public class Main {

    public static void main(String[] args)  {
        
        // Sending message
        List<Message> messages = new ArrayList<>();
        Message message = new Message.Builder()
                .to("ExponentPushToken[xxxxxxxxx_xxx_xxxxx_xx]")
                .title("You've got mail!")
                .body("Check your messages")
                .build();
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
        }
        
        // Later, you can get the Push Receipts
        List<String> ids = new ArrayList<>();
        ids.add("xxxxxxx-yyyy-yyyy-yyyy-xyxyxyxyxyxy");
        try {
            PushReceiptResponse response = ExpoPushClient.getPushReciepts(ids);
            PushReceipt rec = response.getReceipts().get("xxxxxxx-yyyy-yyyy-yyyy-xyxyxyxyxyxy");
            System.out.println(rec.getDetails());
            System.out.println(rec.getStatus());
            System.out.println(rec.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
```
