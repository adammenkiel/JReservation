package pl.publicprojects.jreservation.domain.exception.exceptions;

import pl.publicprojects.jreservation.domain.exception.AppException;

public class ProductNotExistsException extends AppException {
    public ProductNotExistsException() {
        super(404);
    }
    public ProductNotExistsException(String message) {
        super(404, message);
    }
    public ProductNotExistsException(String message, Throwable err) {
        super(404, message, err);
    }
    public ProductNotExistsException(Throwable err) {
        super(404, err);
    }
}
