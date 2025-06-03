

package exception;

import enums.ErrorCode;

/**
 *
 * @author manhphong
 */
public class EntityNotFoundException extends ServiceException{
    public EntityNotFoundException( String message) {
        super(ErrorCode.ERROR_NOT_FOUND, message);
    }

}
