import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class PushReceiptResponse {

    @SerializedName("data")
    private Map<String, PushReceipt> receipts;

    public Map<String, PushReceipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(Map<String, PushReceipt> receipts) {
        this.receipts = receipts;
    }
}
