package pl.publicprojects.jreservation.domain.exception.exceptions;

import pl.publicprojects.jreservation.domain.exception.AppException;

public class WalletNotFoundException extends AppException {
    public WalletNotFoundException() {
        super(404);
    }
    public WalletNotFoundException(String message) {
        super(404, message);
    }
    public WalletNotFoundException(String message, Throwable err) {
        super(404, message, err);
    }
    public WalletNotFoundException(Throwable err) {
        super(404, err);
    }
}
