package pl.publicprojects.jreservation.domain.exception.exceptions;

import pl.publicprojects.jreservation.domain.exception.AppException;

public class ReservationPendingException extends AppException {
    public ReservationPendingException() {
        super(409);
    }
    public ReservationPendingException(String message) {
        super(409, message);
    }
    public ReservationPendingException(String message, Throwable err) {
        super(409, message, err);
    }
    public ReservationPendingException(Throwable err) {
        super(409, err);
    }
}
