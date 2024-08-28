package clearcb.imin.BusinessApi.handler;

import clearcb.imin.BusinessApi.common.domain.model.ErrorListResponse;
import clearcb.imin.BusinessApi.common.domain.model.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorListResponse> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorMessages = new ArrayList<>();

        // Extracting field errors and adding them to the error messages list
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }

        // Creating ErrorResponse with validation error messages
        ErrorListResponse errorResponse = new ErrorListResponse(HttpStatus.BAD_REQUEST.value(), GenericMessageEnum.VALIDATION_FAILED.getMessage(), errorMessages);
        log.error("Validation failed: {}", errorMessages);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), GenericMessageEnum.UNEXPECTED_ERROR.getMessage(), GenericMessageEnum.GENERIC_MESSAGE_ERROR.getMessage());
        log.error("Unexpected error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handlerArgumentException(IllegalArgumentException ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), GenericMessageEnum.UNEXPECTED_ERROR.getMessage(), GenericMessageEnum.GENERIC_MESSAGE_ERROR.getMessage());
        log.error("Unexpected error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handlerRuntimeException(RuntimeException ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_GATEWAY.value(), GenericMessageEnum.UNEXPECTED_ERROR.getMessage(), GenericMessageEnum.GENERIC_MESSAGE_ERROR.getMessage());
        log.error("Unexpected error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorResponse);

    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> noResourceFoundException(NoResourceFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), GenericMessageEnum.GENERIC_NOT_FOUND_MESSAGE.getMessage(), GenericMessageEnum.GENERIC_MESSAGE_ERROR.getMessage());
        log.error("No resource found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), GenericMessageEnum.CONSTRAINT_VIOLATION.getMessage(), GenericMessageEnum.GENERIC_MESSAGE_ERROR.getMessage());
        log.error("Constraint violation: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), GenericMessageEnum.INVALID_ARGUMENT_TYPE.getMessage(), ex.getMessage());
        log.error("Constraint violation: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponse> handleMethodValidationException(HandlerMethodValidationException ex) {

        String errorMessage = Arrays.stream(Objects.requireNonNull(ex.getDetailMessageArguments())).findFirst().orElse(GenericMessageEnum.GENERIC_VALIDATION_MESSAGE.getMessage()).toString(); // Extracting validation error message
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), GenericMessageEnum.CONSTRAINT_VIOLATION.getMessage(), errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }


}
