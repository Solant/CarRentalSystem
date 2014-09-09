package by.skakun.carrentalsystem.util;

import java.util.regex.Pattern;

/**
 *
 * @author Skakun
 *
 * server-side validation of information, which ysers enter into
 * the fields
 */
public class EnteredInfoValidator {

    public static final String LOGIN_REGEX = "^[a-z0-9]{3,16}$"; //big letters
    public static final String EMAIL_REGEX = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    public static final String PASSWORD_REGEX = "^[a-z0-9_-]{6,16}$";
    public static final String PASSNUM_REGEX = "^[A-Z0-9]{7,14}$";
    public static final int MIN_ID = 1;
    public static final int MAX_ID = 10000;
    public static final int MIN_RENT = 1;
    public static final int MAX_RENT = 10000;
    public static final int MAX_DATA_LENGTH = 200;
    public static final int MIN_PRICE = 10;
    public static final int MAX_PRICE = 100000;
    public static final int MIN_PERIOD = 1;
    public static final int MAX_PERIOD = 30;

    public static boolean validateRegistrationInfo(String login, String email, String passNum, String password) {
        return loginVal(login) && passwordVal(password) && passNumVal(passNum) && emailVal(email);
    }
    
    public static boolean validateLoginInfo(String login, String password) {
        return loginValE(login) && passwordValE(password);
    }
    
    public static boolean loginVal(String login) {
        return Pattern.matches(LOGIN_REGEX, login);
    }
    
    public static boolean loginValE(String login) {
        return !login.isEmpty();
    }

    public static boolean emailVal(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
    
    public static boolean emailValSame(String email, String oldEmail) {
        return email.equals(oldEmail);
    }

    public static boolean passwordVal(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }
    public static boolean passwordValSame(String password, String oldPassword) {
        return password.equals(oldPassword);
    }
        public static boolean passwordValE(String password) {
        return !password.isEmpty();
    }

    public static boolean passNumVal(String passNum) {
        return Pattern.matches(PASSNUM_REGEX, passNum);
    }

    public static boolean userIdVal(int userId) {
        return (userId > MIN_ID) && (userId < MAX_ID);
    }

    public static boolean dataLength(String text) {
        return !(text.length() > MAX_DATA_LENGTH) && (text.isEmpty());
    }

    public static boolean priceVal(int price) {
        return (price > MIN_PRICE) && (price < MAX_PRICE);
    }

    public static boolean periodVal(int period) {
        return (period >= MIN_PERIOD) && (period <= MAX_PERIOD);
    }
    
    public static boolean rentPrice(int price) {
        return (price >= MIN_RENT) && (price <= MAX_RENT);
    }
}
