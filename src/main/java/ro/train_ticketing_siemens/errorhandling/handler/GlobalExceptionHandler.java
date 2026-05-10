package ro.train_ticketing_siemens.errorhandling.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.train_ticketing_siemens.errorhandling.enums.ErrorSeverity;
import ro.train_ticketing_siemens.errorhandling.dto.ApiErrorResponse;
import ro.train_ticketing_siemens.errorhandling.exceptions.BaseException;
import ro.train_ticketing_siemens.errorhandling.mapper.ApiErrorResponseMapper;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler implements GlobalExceptionHandlerApi {

    private final ApiErrorResponseMapper mapper;

    @Override
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiErrorResponse> handleBaseException(
            BaseException exception,
            HttpServletRequest request
    ) {
        log.error(
                "{} Exception with code -> {} | Message -> {} | Path -> {}",
                exception.getSeverity(),
                exception.getErrorCode().name(),
                exception.getMessage(),
                request.getRequestURI(),
                exception
        );

        ApiErrorResponse response = mapper.toDto(exception, request.getRequestURI());
        return new ResponseEntity<>(response, response.httpStatus());
    }

    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        log.error(
                "{} Validation failed | Path -> {}",
                ErrorSeverity.ERROR,
                request.getRequestURI(),
                exception
        );

        ApiErrorResponse response = mapper.toDto(exception, request.getRequestURI());
        return new ResponseEntity<>(response, response.httpStatus());
    }

    @Override
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolationException(
            ConstraintViolationException exception,
            HttpServletRequest request
    ) {
        log.error(
                "{} Constraint violation | Path -> {}",
                ErrorSeverity.ERROR,
                request.getRequestURI(),
                exception
        );

        ApiErrorResponse response = mapper.toDto(exception, request.getRequestURI());
        return new ResponseEntity<>(response, response.httpStatus());
    }

    @Override
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleUnexpectedRuntimeException(
            RuntimeException exception,
            HttpServletRequest request
    ) {
        log.error(
                "{} Unexpected runtime error | Path -> {}",
                ErrorSeverity.FATAL,
                request.getRequestURI(),
                exception
        );

        ApiErrorResponse response = mapper.toDto(exception, request.getRequestURI());
        return new ResponseEntity<>(response, response.httpStatus());
    }
}
