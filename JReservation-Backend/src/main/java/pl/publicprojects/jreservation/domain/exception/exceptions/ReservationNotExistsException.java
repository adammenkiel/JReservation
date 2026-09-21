package pl.publicprojects.jreservation.domain.exception.exceptions;

import pl.publicprojects.jreservation.domain.exception.AppException;

public class ReservationNotExistsException extends AppException {
    public ReservationNotExistsException() {
        super(404);
    }
    public ReservationNotExistsException(String message) {
        super(404, message);
    }
    public ReservationNotExistsException(String message, Throwable err) {
        super(404, message, err);
    }
    public ReservationNotExistsException(Throwable err) {
        super(404, err);
    }
}
