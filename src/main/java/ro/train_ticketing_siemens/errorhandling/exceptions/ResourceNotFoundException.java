package ro.train_ticketing_siemens.errorhandling.exceptions;

import org.springframework.http.HttpStatus;
import ro.train_ticketing_siemens.errorhandling.enums.ErrorCode;
import ro.train_ticketing_siemens.errorhandling.enums.ErrorSeverity;

public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException(ErrorCode errorCode, Object... props) {
        super(errorCode, HttpStatus.NOT_FOUND, ErrorSeverity.ERROR, props);
    }
}