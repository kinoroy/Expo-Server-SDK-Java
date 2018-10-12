public class PushToken {

   private static final String PATTERN = "^[a-z\\d]{8}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{12}$";

   private String value;

   public boolean isValid() {
       return value.matches(PATTERN);
   }

   public static boolean isValid(String token) {
       return token.matches(PATTERN);
   }

   @Override
    public String toString() {
       return "ExponentPushToken[" + value + ']';
   }

}
