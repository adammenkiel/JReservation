package pl.publicprojects.jreservation.domain.exception.exceptions;

import pl.publicprojects.jreservation.domain.exception.AppException;

public class ProductOutOfStockException extends AppException {
    public ProductOutOfStockException() {
        super(409);
    }
    public ProductOutOfStockException(String message) {
        super(409, message);
    }
    public ProductOutOfStockException(String message, Throwable err) {
        super(409, message, err);
    }
    public ProductOutOfStockException(Throwable err) {
        super(409, err);
    }
}
