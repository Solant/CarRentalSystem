package by.skakun.carrentalsystem.exception;

public class CarException extends EntityException{

    public CarException() {
    }

    public CarException(String msg) {
        super(msg);
    }

    public CarException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarException(Throwable cause) {
        super(cause);
    }

    protected CarException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
