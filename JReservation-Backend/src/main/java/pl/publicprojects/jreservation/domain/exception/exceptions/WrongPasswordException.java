package pl.publicprojects.jreservation.domain.exception.exceptions;

import pl.publicprojects.jreservation.domain.exception.AppException;

public class WrongPasswordException extends AppException {
    public WrongPasswordException() {
        super(401);
    }
    public WrongPasswordException(String message) {
        super(401, message);
    }
    public WrongPasswordException(String message, Throwable err) {
        super(401, message, err);
    }
    public WrongPasswordException(Throwable err) {
        super(401, err);
    }
}
