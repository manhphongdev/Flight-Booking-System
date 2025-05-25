package utils;



import java.math.BigDecimal;
import java.util.regex.Pattern;

public class ValidatorUtils {

    private ValidatorUtils() {
        throw new UnsupportedOperationException("Utility class, cannot be instantiated.");
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]{1,64}@[A-Za-z0-9.-]{1,255}\\.[A-Za-z]{2,6}$");
    private static final Pattern PHONE_VN_PATTERN = Pattern.compile("^(0|\\+84)[1-9][0-9]{8}$");
    private static final Pattern PHONE_GLOBAL_PATTERN = Pattern.compile("^\\+?[0-9]{10,15}$");
    private static final Pattern ID_CARD_PATTERN = Pattern.compile("^\\d{12}$");

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isStringEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static boolean isValidPhoneVN(String phoneNumber) {
        return phoneNumber != null && PHONE_VN_PATTERN.matcher(phoneNumber).matches();
    }

    public static boolean isValidPhoneGlobal(String phoneNumber) {
        return phoneNumber != null && PHONE_GLOBAL_PATTERN.matcher(phoneNumber).matches();
    }

    public static boolean isWithinIntRange(int num, int min, int max) {
        return num >= min && num <= max;
    }

    public static boolean isWithinDoubleRange(double num, double min, double max) {
        return num >= min && num <= max;
    }

    public static boolean isWithinLongRange(long num, long min, long max) {
        return num >= min && num <= max;
    }

    public static boolean isValidIdentityCard(String cardNumber) {
        return cardNumber != null && ID_CARD_PATTERN.matcher(cardNumber).matches();
    }

    public static boolean isValidAmount(String amount, BigDecimal min, BigDecimal max) {
        try {
            BigDecimal value = new BigDecimal(amount);
            return value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}