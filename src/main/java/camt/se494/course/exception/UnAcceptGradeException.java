package camt.se494.course.exception;

/**
 * Created by Dto on 10/1/2015.
 */
public class UnAcceptGradeException extends RuntimeException {
    public UnAcceptGradeException(){
        super();
    }

    public UnAcceptGradeException(String message) {
        super(message);
    }

    public UnAcceptGradeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAcceptGradeException(Throwable cause) {
        super(cause);
    }

    public UnAcceptGradeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
