import com.google.gson.annotations.SerializedName;

public enum PushReceiptError {

    /**
     * The device cannot receive push notifications anymore and you should stop sending messages to the corresponding Expo push token
     */
    @SerializedName("DeviceNotRegistered")
    DEVICE_NOT_REGISTERED,
    /**
     * The total notification payload was too large. On Android and iOS the total payload must be at most 4096 bytes.
     */
    @SerializedName("MessageTooBig")
    MESSAGE_TOO_BIG,
    /**
     * You are sending messages too frequently to the given device. Implement exponential backoff and slowly retry sending messages.
     */
    @SerializedName("MessageRateExceeded")
    MESSAGE_RATE_EXCEEDED,
    /**
     * Your push notification credentials for your standalone app are invalid (ex: you may have revoked them)
     */
    @SerializedName("InvalidCredentials")
    INVALID_CREDENTIALS;

}
