package pl.publicprojects.jreservation.infrastructure.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.publicprojects.jreservation.domain.exception.AppException;
import pl.publicprojects.jreservation.infrastructure.config.ConfigProperties;

import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final boolean debug;

    public GlobalExceptionHandler(ConfigProperties configProperties) {
        this.debug = configProperties.isDevDebug();
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleAppError(AppException appException) {
        return ResponseEntity.status(appException.getErrorCode())
                .body(appException.getMessage());
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<?> handleAuthError(InternalAuthenticationServiceException authException) {
        return ResponseEntity.status(401)
                .body(authException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownError(Exception e) {
        if(this.debug) e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong!");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> invalidMethodHandler(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
    }
}
