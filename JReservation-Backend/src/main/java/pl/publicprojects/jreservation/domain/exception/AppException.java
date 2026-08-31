package pl.publicprojects.jreservation.domain.exception;

import lombok.Getter;

@Getter
public abstract class AppException extends RuntimeException {

    private final int errorCode;

    public AppException(int errorCode) {
        this.errorCode = errorCode;
    }

    public AppException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    public AppException(int errorCode, String message, Throwable err) {
        super(message, err);
        this.errorCode = errorCode;
    }
    public AppException(int errorCode, Throwable err) {
        super(err);
        this.errorCode = errorCode;
    }
}
