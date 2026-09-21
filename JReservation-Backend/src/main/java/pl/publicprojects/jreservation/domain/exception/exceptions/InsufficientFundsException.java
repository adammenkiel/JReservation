package pl.publicprojects.jreservation.domain.exception.exceptions;

import pl.publicprojects.jreservation.domain.exception.AppException;

public class InsufficientFundsException extends AppException {
    public InsufficientFundsException() {
        super(422);
    }
    public InsufficientFundsException(String message) {
        super(422, message);
    }
    public InsufficientFundsException(String message, Throwable err) {
        super(422, message, err);
    }
    public InsufficientFundsException(Throwable err) {
        super(422, err);
    }
}
