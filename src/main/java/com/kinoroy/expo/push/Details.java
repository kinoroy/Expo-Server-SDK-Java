package com.kinoroy.expo.push;

import com.google.gson.annotations.SerializedName;

/**
 * An object containing further details on the status of a PushTicket or PushReceipt
 */
public class Details {

    @SerializedName("error")
    private PushError error;

    private Details() {}

    public PushError getError() {
        return error;
    }

}
