package ro.train_ticketing_siemens.errorhandling.dto;

import ro.train_ticketing_siemens.errorhandling.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public record ApiErrorResponse(
        HttpStatus httpStatus,
        ErrorCode errorCode,
        String message,
        String path
) {
}
