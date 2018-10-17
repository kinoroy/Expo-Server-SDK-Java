package com.kinoroy.expo.push;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PushTicketResponse {

    @SerializedName("data")
    private List<PushTicket> tickets;


    @SerializedName("errors")
    private List<ExpoError> errors;

    private PushTicketResponse() {}

    /**
     * @return A list of push tickets each of which corresponds to the message at its respective index in the request
     */
    public List<PushTicket> getTickets() {
        return tickets;
    }

    /**
     *  @return A list of errors (usually just one) if there is an error with the entire request. If not, then null
     *
     */
    public List<ExpoError> getErrors() {
        return errors;
    }

}
