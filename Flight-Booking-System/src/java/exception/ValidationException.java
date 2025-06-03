package exception;

import enums.ErrorCode;

/**
 *
 * @author manhphong
 */
public class ValidationException extends ServiceException {

    public ValidationException(String message) {
        super(ErrorCode.ERROR_VALIDATE, message);
    }

}
