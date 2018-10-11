import com.google.gson.annotations.SerializedName;

public class PushReceipt {

    @SerializedName("status")
    private Status status;

    @SerializedName("message")
    private String message;

    @SerializedName("details")
    private Details details;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public static final class Details {

        @SerializedName("error")
        private PushReceiptError error;

        public PushReceiptError getError() {
            return error;
        }

        public void setError(PushReceiptError error) {
            this.error = error;
        }

    }

}
