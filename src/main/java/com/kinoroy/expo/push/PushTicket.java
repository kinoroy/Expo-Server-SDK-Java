package com.kinoroy.expo.push;

import com.google.gson.annotations.SerializedName;

public class PushTicket {

    @SerializedName("status")
    private Status status;

    @SerializedName("id")
    private String id;

    @SerializedName("message")
    private String message;

    @SerializedName("details")
    private Details details;

    private PushTicket() {}

    /**
     * @return A status indicating whether Expo successfully received the notification
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @return an id to use to later get the receipt for the message
     */
    public String getId() {
        return id;
    }

    /**
     * @return a message from Expo's server (usually has a human friendly description of any error)
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return A details object with further information on the status of the ticket
     */
    public Details getDetails() {
        return details;
    }

}
