import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class PushReceiptResponse {

    @SerializedName("data")
    private Map<String, PushReceipt> receipts;

}
