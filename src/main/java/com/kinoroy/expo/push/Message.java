package com.kinoroy.expo.push;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

/**
 * Represents a notification to be sent to an individual device
 */
public final class Message {

    private String to;

    private HashMap<String, Object> data;

    private String title;

    private String body;

    private String sound = "default";

    private Duration ttl;

    private Instant expiration;

    private Priority priority;

    private Integer badge;

    private String channelId;

    private Message() {}

    /**
     * @return A token of the form ExponentPushToken[xxxxxxx]
     */
    public String getTo() {
        return to;
    }

    /**
     * @return A dict of extra data to pass inside of the push notification.
     * The total notification payload must be at most 4096 bytes.
     */
    public Object getData() {
        return data;
    }

    /**
     * @return The title to display in the notification. On iOS, this is
     * displayed only on Apple Watch.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The message to display in the notification.
     */
    public String getBody() {
        return body;
    }

    /**
     * @return A sound to play when the recipient receives this
     * notification. Specify "default" to play the device's default
     * notification sound, or omit this field to play no sound.
     * iOS specific.
     */
    public String getSound() {
        return sound;
    }

    /**
     * @return The amount of time for which the message may be kept around
     * for redelivery if it hasn't been delivered yet. Defaults to 0.
     */
    public Duration getTtl() {
        return ttl;
    }

    /**
     * @return Date for when this message expires. It has
     * the same effect as ttl, and is just an absolute date
     * instead of a relative timestamp.
     */
    public Instant getExpiration() {
        return expiration;
    }

    /**
     * @return Delivery priority of the message. 'default', 'normal',
     * and 'high' are the only valid values.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * @return A non-negative integer representing the unread notification count. This
     * currently only affects iOS. 0 will clear the badge count.
     */
    public Integer getBadge() {
        return badge;
    }

    /**
     * @return The ID of the Notification Channel through which to display
     * this notification on Android devices.
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * A class to help contruct messages
     */
    public static class Builder {

        private String to;

        private HashMap<String, Object> data;

        private String title;

        private String body;

        private String sound = "default";

        private Duration ttl;

        private Instant expiration;

        private Priority priority;

        private Integer badge;

        private String channelId;

        /**
         * @param to A token of the form ExponentPushToken[xxxxxxx]
         */
        public Builder to(String to) {
            this.to = to;
            return this;
        }

        /**
         * @param data A dict of extra data to pass inside of the push notification.
         * The total notification payload must be at most 4096 bytes.
         */
        public Builder data(HashMap<String, Object> data) {
            this.data = data;
            return this;
        }

        /**
         * @param title The title to display in the notification. On iOS, this is
         * displayed only on Apple Watch.
         */
        public Builder title(String title) {
            this.title = title;
            return this;
        }

        /**
         * @param body The message to display in the notification.
         */
        public Builder body(String body) {
            this.body = body;
            return this;
        }

        /**
         * @param sound A sound to play when the recipient receives this
         * notification. Specify "default" to play the device's default
         * notification sound, or omit this field to play no sound.
         * iOS specific.
         */
        public Builder sound(String sound) {
            this.sound = sound;
            return this;
        }

        /**
         * @param ttl The number of seconds for which the message may be kept around
         * for redelivery if it hasn't been delivered yet. Defaults to 0.
         */
        public Builder ttl(Duration ttl) {
            this.ttl = ttl;
            return this;
        }

        /**
         * @param expiration Date for when this message expires. It has
         * the same effect as ttl, and is just an absolute date
         * instead of a relative timestamp.
         */
        public Builder expiration(Instant expiration) {
            this.expiration = expiration;
            return this;
        }

        /**
         * @param priority Delivery priority of the message. 'default', 'normal',
         * and 'high' are the only valid values.
         */
        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        /**
         * @param badge A non-negative integer representing the unread notification count. This
         * currently only affects iOS. 0 will clear the badge count.
         */
        public Builder badge(Integer badge) {
            this.badge = badge;
            return this;
        }

        /**
         * @param channelId The ID of the Notification Channel through which to display
         * this notification on Android devices.
         */
        public Builder channelId(String channelId) {
            this.channelId = channelId;
            return this;
        }

        public Message build() {
            Message message = new Message();

            if (to == null) {
                throw new IllegalStateException("\"to\" cannot be null");
            }
            message.to = to;
            message.data = data;
            message.title = title;
            message.body = body;
            message.sound = sound;
            if (ttl != null && ttl.isNegative()) {
                throw new IllegalStateException("\"ttl\" cannot be negative");
            }
            message.ttl = ttl;
            message.expiration = expiration;
            message.priority = priority;
            if (badge < 0) {
                throw new IllegalStateException("\"badge\" cannot be negative");
            }
            message.badge = badge;
            message.channelId = channelId;

            return message;
        }

    }

}
