package com.kinoroy.expo.push;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PushTicketResponse {

    @SerializedName("data")
    private List<PushTicket> tickets;

    /**
     *  If there is an error with the entire request, the HTTP status code will be 4xx or 5xx,
     *  and errors will be an array of error objects (usually just one):
     */
    @SerializedName("errors")
    private List<ExpoError> errors;

    private PushTicketResponse() {}

    public List<PushTicket> getTickets() {
        return tickets;
    }

    public List<ExpoError> getErrors() {
        return errors;
    }

}
