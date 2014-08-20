package by.skakun.carrentalsystem.exception;

/**
 *
 * @author Skakun
 */
public abstract class EntityException extends Exception {

    public EntityException() {
    }

    public EntityException(String msg) {
        super(msg);
    }

    public EntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityException(Throwable cause) {
        super(cause);
    }

    protected EntityException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
