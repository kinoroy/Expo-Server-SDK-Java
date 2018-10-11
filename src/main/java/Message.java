import java.util.Date;

public class Message {

    /**
     * A token of the form ExponentPushToken[xxxxxxx]
     */
    private String to;

    /**
     * A dict of extra data to pass inside of the push notification.
     * The total notification payload must be at most 4096 bytes.
     */
    private Object data;

    /**
     * The title to display in the notification. On iOS, this is
     * displayed only on Apple Watch.
     */
    private String title;

    /**
     * The message to display in the notification.
     */
    private String body;

    /**
     * A sound to play when the recipient receives this
     * notification. Specify "default" to play the device's default
     * notification sound, or omit this field to play no sound.
     * iOS specific.
     */
    private String sound = "default";

    /**
     * The number of seconds for which the message may be kept around
     * for redelivery if it hasn't been delivered yet. Defaults to 0.
     */
    private Long ttl;

    /**
     * UNIX timestamp for when this message expires. It has
     * the same effect as ttl, and is just an absolute timestamp
     * instead of a relative one.
     */
    private Date expiration;

    /**
     * Delivery priority of the message. 'default', 'normal',
     * and 'high' are the only valid values.
     */
    private Priority priority;

    /**
     * An integer representing the unread notification count. This
     * currently only affects iOS. Specify 0 to clear the badge count.
     */
    private Integer badge;

    /**
     * ID of the Notification Channel through which to display
     * this notification on Android devices.
     */
    private Long channelId;
}
