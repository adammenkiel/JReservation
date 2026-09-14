package pl.publicprojects.jreservation.domain.exception.exceptions;

import pl.publicprojects.jreservation.domain.exception.AppException;

public class ProductNotAvailableException extends AppException {
    public ProductNotAvailableException() {
        super(409);
    }
    public ProductNotAvailableException(String message) {
        super(409, message);
    }
    public ProductNotAvailableException(String message, Throwable err) {
        super(409, message, err);
    }
    public ProductNotAvailableException(Throwable err) {
        super(409, err);
    }
}
