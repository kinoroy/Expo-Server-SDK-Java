import com.google.gson.annotations.SerializedName;

public class PushTicket {

    /**
     * Indicates whether Expo successfully received the notification
     */
    @SerializedName("status")
    private Status status;

    @SerializedName("id")
    private String id;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
