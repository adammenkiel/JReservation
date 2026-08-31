package pl.publicprojects.jreservation.domain.exception.exceptions;

import pl.publicprojects.jreservation.domain.exception.AppException;

public class AuthException extends AppException {
    public AuthException() {
        super(403);
    }
    public AuthException(String message) {
        super(403, message);
    }
    public AuthException(String message, Throwable err) {
        super(403, message, err);
    }
    public AuthException(Throwable err) {
        super(403, err);
    }
}
