package com.kinoroy.expo.push;

import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("error")
    private PushError error;

    private Details() {}

    public PushError getError() {
        return error;
    }

}
