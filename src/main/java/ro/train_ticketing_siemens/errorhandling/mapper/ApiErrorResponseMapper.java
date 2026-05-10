package ro.train_ticketing_siemens.errorhandling.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ro.train_ticketing_siemens.errorhandling.enums.ErrorCode;
import ro.train_ticketing_siemens.errorhandling.dto.ApiErrorResponse;

import jakarta.validation.ConstraintViolationException;
import ro.train_ticketing_siemens.errorhandling.exceptions.BaseException;

import java.util.stream.Collectors;

@Component
public class ApiErrorResponseMapper {

    public ApiErrorResponse toDto(BaseException exception, String path) {
        return new ApiErrorResponse(
                exception.getHttpStatus(),
                exception.getErrorCode(),
                exception.getMessage(),
                path
        );
    }

    public ApiErrorResponse toDto(MethodArgumentNotValidException exception, String path) {
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                ErrorCode._0003_VALIDATION_ERROR,
                ErrorCode._0003_VALIDATION_ERROR.formatBaseMessage(message),
                path
        );
    }

    public ApiErrorResponse toDto(ConstraintViolationException exception, String path) {
        return new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                ErrorCode._0004_CONSTRAINT_VIOLATION,
                ErrorCode._0004_CONSTRAINT_VIOLATION.formatBaseMessage(exception.getMessage()),
                path
        );
    }

    public ApiErrorResponse toDto(RuntimeException exception, String path) {
        return new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCode._0002_INTERNAL_ERROR,
                ErrorCode._0002_INTERNAL_ERROR.formatBaseMessage(),
                path
        );
    }
}
