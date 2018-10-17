package com.kinoroy.expo.push;

import com.google.gson.annotations.SerializedName;

/**
 * Represents an error that Expo encountered with satisfying an entire request to their server
 */
public class ExpoError {

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    private ExpoError() {}

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
