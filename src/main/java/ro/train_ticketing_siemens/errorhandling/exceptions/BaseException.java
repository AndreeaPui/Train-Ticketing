package ro.train_ticketing_siemens.errorhandling.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import ro.train_ticketing_siemens.errorhandling.enums.ErrorCode;
import ro.train_ticketing_siemens.errorhandling.enums.ErrorSeverity;

@Getter
public abstract class BaseException extends RuntimeException {

    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;
    private final ErrorSeverity severity;

    protected BaseException(
            ErrorCode errorCode,
            HttpStatus httpStatus,
            ErrorSeverity severity,
            Object... props
    ) {
        super(errorCode.formatBaseMessage(props));
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.severity = severity;
    }
}