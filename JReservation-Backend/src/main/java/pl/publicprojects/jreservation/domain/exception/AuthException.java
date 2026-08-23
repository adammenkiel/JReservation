package pl.publicprojects.jreservation.domain.exception;

public class AuthException extends RuntimeException {
    public AuthException() {}

    public AuthException(String message) {
        super(message);
    }
    public AuthException(String message, Throwable err) {
        super(message, err);
    }
    public AuthException(Throwable err) {
        super(err);
    }
}
