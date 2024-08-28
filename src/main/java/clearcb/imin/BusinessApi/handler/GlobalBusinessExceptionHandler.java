package clearcb.imin.BusinessApi.handler;

import clearcb.imin.BusinessApi.common.domain.exception.CustomBadCredentialsException;
import clearcb.imin.BusinessApi.common.domain.exception.DatabaseError;
import clearcb.imin.BusinessApi.common.domain.exception.InvalidUUIDFormatException;
import clearcb.imin.BusinessApi.common.domain.model.ErrorResponse;
import clearcb.imin.BusinessApi.event.domain.exceptions.EventNotFoundError;
import clearcb.imin.BusinessApi.event.domain.exceptions.EventValidationError;
import clearcb.imin.BusinessApi.event.domain.model.EventMessageEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalBusinessExceptionHandler {

    // Event exceptions

    @ExceptionHandler(EventValidationError.class)
    public ResponseEntity<ErrorResponse> handleEventValidationException(EventValidationError ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), EventMessageEnum.EVENT_CREATE_KO.getMessage(), ex.getMessage());
        log.error("Validation failed: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }

    @ExceptionHandler(EventNotFoundError.class)
    public ResponseEntity<ErrorResponse> handleEventNotFoundException(EventNotFoundError ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), EventMessageEnum.EVENT_NOT_FOUND.getMessage(), ex.getMessage());
        log.error("Event not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

    }

    // Auth exceptions

    @ExceptionHandler(CustomBadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleLocationNotFoundException(CustomBadCredentialsException ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), GenericMessageEnum.BAD_CREDENTIALS.getMessage(), ex.getMessage());
        log.error("Bad credentials: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);

    }

    // Database exceptions

    @ExceptionHandler(DatabaseError.class)
    public ResponseEntity<ErrorResponse> databaseErrorException(DatabaseError ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), GenericMessageEnum.UNEXPECTED_DATABASE_ERROR.getMessage(), ex.getMessage());
        log.error("Unexpected database error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }

    // Validation exceptions

    @ExceptionHandler(InvalidUUIDFormatException.class)
    public ResponseEntity<ErrorResponse> handlerInvalidUUIDException(InvalidUUIDFormatException ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), GenericMessageEnum.UNEXPECTED_ERROR.getMessage(), GenericMessageEnum.INVALID_UUID_FORMAT.getMessage());
        log.error("Invalid UUID format: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }

}
