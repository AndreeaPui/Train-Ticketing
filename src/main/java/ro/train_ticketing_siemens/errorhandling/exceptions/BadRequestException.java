package ro.train_ticketing_siemens.errorhandling.exceptions;


import org.springframework.http.HttpStatus;
import ro.train_ticketing_siemens.errorhandling.enums.ErrorCode;
import ro.train_ticketing_siemens.errorhandling.enums.ErrorSeverity;

public class BadRequestException extends BaseException {

    public BadRequestException(ErrorCode errorCode, Object... props) {
        super(errorCode, HttpStatus.BAD_REQUEST, ErrorSeverity.WARNING, props);
    }
}
