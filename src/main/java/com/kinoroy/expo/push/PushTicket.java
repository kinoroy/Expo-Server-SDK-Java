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
}
