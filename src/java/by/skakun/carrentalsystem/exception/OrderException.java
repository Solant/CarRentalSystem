package by.skakun.carrentalsystem.exception;

/**
 *
 * @author Skakun
 */
public class OrderException extends EntityException {

    public OrderException() {
    }

    public OrderException(String msg) {
        super(msg);
    }

    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderException(Throwable cause) {
        super(cause);
    }

    protected OrderException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
