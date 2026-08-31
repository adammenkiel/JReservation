package pl.publicprojects.jreservation.infrastructure.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.publicprojects.jreservation.domain.exception.AppException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleAppError(AppException appException) {
        return ResponseEntity.status(appException.getErrorCode())
                .body(appException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong!");
    }
}
