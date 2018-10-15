package com.kinoroy.expo.push;

import com.google.gson.annotations.SerializedName;

public class PushTicket {

    /**
     * Indicates whether Expo successfully received the notification
     */
    @SerializedName("status")
    private Status status;

    @SerializedName("id")
    private String id;

    @SerializedName("message")
    private String message;

    @SerializedName("details")
    private Details details;

    private PushTicket() {}

    public Status getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Details getDetails() {
        return details;
    }

}
