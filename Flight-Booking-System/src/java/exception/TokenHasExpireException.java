
package exception;

import enums.ErrorCode;

/**
 *
 * @author manhphong
 */
public class TokenHasExpireException extends ServiceException{

    public TokenHasExpireException( String message) {
        super(ErrorCode.TOKEN_EXPIRED, message);
    }

}
