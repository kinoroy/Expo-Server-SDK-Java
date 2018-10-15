package com.kinoroy.expo.push;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class PushReceiptResponse {

    @SerializedName("data")
    private Map<String, PushReceipt> receipts;

    private PushReceiptResponse() {}

    public Map<String, PushReceipt> getReceipts() {
        return receipts;
    }

}
