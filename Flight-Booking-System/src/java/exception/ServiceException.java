
package exception;

import enums.ErrorCode;


/**
 *
 * @author manhphong
 */
public class ServiceException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;

    public ServiceException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
