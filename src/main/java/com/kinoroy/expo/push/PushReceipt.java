package com.kinoroy.expo.push;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a push receipt retrieved from Expo's servers
 */
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

    public String getMessage() {
        return message;
    }

    public Details getDetails() {
        return details;
    }

}
