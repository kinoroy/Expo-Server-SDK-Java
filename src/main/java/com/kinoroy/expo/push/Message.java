package com.kinoroy.expo.push;

import java.util.Date;
import java.util.HashMap;

public final class Message {

    /**
     * A token of the form ExponentPushToken[xxxxxxx]
     */
    private String to;

    /**
     * A dict of extra data to pass inside of the push notification.
     * The total notification payload must be at most 4096 bytes.
     */
    private HashMap<String, Object> data;

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

    private Message() {}

    public String getTo() {
        return to;
    }

    public Object getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getSound() {
        return sound;
    }

    public Long getTtl() {
        return ttl;
    }

    public Date getExpiration() {
        return expiration;
    }

    public Priority getPriority() {
        return priority;
    }

    public Integer getBadge() {
        return badge;
    }

    public Long getChannelId() {
        return channelId;
    }

    public static class Builder {

        /**
         * A token of the form ExponentPushToken[xxxxxxx]
         */
        private String to;

        /**
         * A dict of extra data to pass inside of the push notification.
         * The total notification payload must be at most 4096 bytes.
         */
        private HashMap<String, Object> data;

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

        public String getTo() {
            return to;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Object getData() {
            return data;
        }

        public Builder data(HashMap<String, Object> data) {
            this.data = data;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public String getBody() {
            return body;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public String getSound() {
            return sound;
        }

        public Builder sound(String sound) {
            this.sound = sound;
            return this;
        }

        public Long getTtl() {
            return ttl;
        }

        public Builder ttl(Long ttl) {
            this.ttl = ttl;
            return this;
        }

        public Date getExpiration() {
            return expiration;
        }

        public Builder expiration(Date expiration) {
            this.expiration = expiration;
            return this;
        }

        public Priority getPriority() {
            return priority;
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Integer getBadge() {
            return badge;
        }

        public Builder badge(Integer badge) {
            this.badge = badge;
            return this;
        }

        public Long getChannelId() {
            return channelId;
        }

        public Builder channelId(Long channelId) {
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
            if (ttl < 0) {
                throw new IllegalStateException("\"ttl\" cannot be negative");
            }
            message.ttl = ttl;
            message.expiration = expiration;
            message.priority = priority;
            if (badge < 0) {
                throw new IllegalStateException("\"badge\" cannot be negative");
            }
            message.badge = badge;

            return message;
        }

    }

}
