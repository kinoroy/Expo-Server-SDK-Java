import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PushTicketResponse {

    @SerializedName("data")
    private List<PushTicket> tickets;

    public List<PushTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<PushTicket> tickets) {
        this.tickets = tickets;
    }

}
