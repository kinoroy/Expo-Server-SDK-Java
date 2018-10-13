package com.kinoroy.expo.push;

import java.util.regex.Pattern;

public class PushToken {

   private static final Pattern PATTERN = Pattern.compile("^[a-z\\d]{8}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{12}$");

   private String value;

   public PushToken(String token) throws IllegalArgumentException {
       this.value = token;
       if (!this.isValid()) {
           throw new IllegalArgumentException("Token is not a valid ExpoPushToken");
       }
   }

   public boolean isValid() {
       boolean valid = (value.startsWith("ExponentPushToken[") ||
               value.startsWith("ExpoPushToken[")) &&
               value.endsWith("]") &&
               PATTERN.matcher(value).find();
       return valid;
   }

   public static boolean isValid(String token) {
       boolean valid = (token.startsWith("ExponentPushToken[") ||
               token.startsWith("ExpoPushToken[")) &&
               token.endsWith("]") &&
               PATTERN.matcher(token).find();
       return valid;
   }

   @Override
    public String toString() {
       return "ExponentPushToken[" + value + ']';
   }

}
