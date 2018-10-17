package com.kinoroy.expo.push;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Represents a request to Expo's servers to retrieve push receipts
 */
public class PushReceiptRequest {

    @SerializedName("ids")
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds (List<String> ids) {
        this.ids = ids;
    }

}
