package com.kinoroy.expo.push;

import com.google.gson.annotations.SerializedName;

public enum Priority {

    /**
     * Default is Normal on Android, High on iOS
     */
    @SerializedName("default")
    DEFAULT,
    /**
     * Android: won't open network connections on sleeping devices and delivery may be delayed
     * iOS: sent at a time which takes into account power considerations. May be grouped or delivered in bursts
     *      Throttled and may not be delivered by Apple. Corresponds to APNs priority level 5
     */
    @SerializedName("normal")
    NORMAL,
    /**
     * Android: delivered immediately if possible and may wake sleeping devices to open network connections
     * iOS: sent immediately. Corresponds to APNs priority level 10
     */
    @SerializedName("high")
    HIGH;

}
