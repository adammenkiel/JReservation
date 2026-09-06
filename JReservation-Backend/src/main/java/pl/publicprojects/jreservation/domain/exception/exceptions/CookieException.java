package pl.publicprojects.jreservation.domain.exception.exceptions;

import pl.publicprojects.jreservation.domain.exception.AppException;

public class CookieException extends AppException {
    public CookieException() {
        super(400);
    }
    public CookieException(String message) {
        super(400, message);
    }
    public CookieException(String message, Throwable err) {
        super(400, message, err);
    }
    public CookieException(Throwable err) {
        super(400, err);
    }
}
