package by.skakun.carrentalsystem.util;

import java.util.regex.Pattern;

/**
 *
 * @author Skakun
 *
 * server-side validation
 */
public class EnteredInfoValidator {

    public static final String loginRegex = "^[a-z0-9]{3,16}$";
    public static final String emailRegex = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    public static final String passwordRegex = "^[a-z0-9_-]{6,16}$";
    public static final String passNumRegex = "^[A-Z0-9]{7,14}$";
    public static final int minId = 1;
    public static final int maxId = 10000;
    public static final int maxDataLength = 200;
    public static final int minPrice = 10;
    public static final int maxPrice = 100000;
    public static final int minPeriod = 1;
    public static final int maxPeriod = 30;

    public static boolean validateRegistrationInfo(String login, String email, String passNum, String password) {
        return loginVal(login) && passwordVal(password) && passNumVal(passNum) && emailVal(email);
    }
    
  //  public static boolean validateOrderInfo(){
        
  //  }
    
    public static boolean loginVal(String login) {
        return Pattern.matches(loginRegex, login);
    }

    public static boolean emailVal(String email) {
        return Pattern.matches(emailRegex, email);
    }

    public static boolean passwordVal(String password) {
        return Pattern.matches(passwordRegex, password);
    }

    public static boolean passNumVal(String passNum) {
        return Pattern.matches(passNumRegex, passNum);
    }

    public static boolean userIdVal(int userId) {
        return (userId > minId) && (userId < maxId);
    }

    public static boolean dataLength(String text) {
        return !(text.length() > 200) && (text.isEmpty());
    }

    public static boolean priceVal(int price) {
        return (price > minPrice) && (price < maxPrice);
    }

    public static boolean periodVal(int period) {
        return (period >= minPeriod) && (period <= maxPeriod);
    }
}
