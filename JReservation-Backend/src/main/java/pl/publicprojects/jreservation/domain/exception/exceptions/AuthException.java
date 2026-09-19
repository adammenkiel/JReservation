package pl.publicprojects.jreservation.domain.exception.exceptions;

import pl.publicprojects.jreservation.domain.exception.AppException;

public class AuthException extends AppException {
    public AuthException() {
        super(401);
    }
    public AuthException(String message) {
        super(401, message);
    }
    public AuthException(String message, Throwable err) {
        super(401, message, err);
    }
    public AuthException(Throwable err) {
        super(401, err);
    }
}
