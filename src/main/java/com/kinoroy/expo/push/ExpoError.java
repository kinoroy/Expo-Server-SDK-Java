package com.kinoroy.expo.push;

import com.google.gson.annotations.SerializedName;

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
