package pl.publicprojects.jreservation.domain.exception.exceptions;

import pl.publicprojects.jreservation.domain.exception.AppException;

public class InvalidCurrencyException extends AppException {
    public InvalidCurrencyException() {
        super(409);
    }
    public InvalidCurrencyException(String message) {
        super(409, message);
    }
    public InvalidCurrencyException(String message, Throwable err) {
        super(409, message, err);
    }
    public InvalidCurrencyException(Throwable err) {
        super(409, err);
    }
}
