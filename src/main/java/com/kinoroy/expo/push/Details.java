package com.kinoroy.expo.push;

import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("error")
    private PushError error;

    public PushError getError() {
        return error;
    }

    public void setError(PushError error) {
        this.error = error;
    }

}
