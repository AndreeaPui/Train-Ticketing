package ro.train_ticketing_siemens.errorhandling.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ro.train_ticketing_siemens.errorhandling.dto.ApiErrorResponse;
import ro.train_ticketing_siemens.errorhandling.exceptions.BaseException;

public interface GlobalExceptionHandlerApi {

    ResponseEntity<ApiErrorResponse> handleBaseException(BaseException exception, HttpServletRequest request);

    ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    );

    ResponseEntity<ApiErrorResponse> handleConstraintViolationException(
            ConstraintViolationException exception,
            HttpServletRequest request
    );

    ResponseEntity<ApiErrorResponse> handleUnexpectedRuntimeException(
            RuntimeException exception,
            HttpServletRequest request
    );
}
