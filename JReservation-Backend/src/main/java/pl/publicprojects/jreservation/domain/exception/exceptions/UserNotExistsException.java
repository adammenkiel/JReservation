package pl.publicprojects.jreservation.domain.exception.exceptions;

import pl.publicprojects.jreservation.domain.exception.AppException;

public class UserNotExistsException extends AppException {
    public UserNotExistsException() {
        super(401);
    }
    public UserNotExistsException(String message) {
        super(401, message);
    }
    public UserNotExistsException(String message, Throwable err) {
        super(401, message, err);
    }
    public UserNotExistsException(Throwable err) {
        super(401, err);
    }
}
