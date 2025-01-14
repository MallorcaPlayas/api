package org.example.apirest.error;

import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BeachNotFoundException.class)
    public ResponseEntity<ApiError> handleRestaurantNotFound(BeachNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(@NonNull Exception ex,
                                                             Object body,
                                                             @NonNull HttpHeaders headers,
                                                             @NonNull HttpStatusCode statusCode,
                                                             @NonNull WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.valueOf(statusCode.value()), ex.getMessage());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
