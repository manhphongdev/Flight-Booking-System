/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package exception;

import enums.ErrorCode;

/**
 *
 * @author manhphong
 */
public class InvalidTokenException extends ServiceException{

    public InvalidTokenException( String message) {
        super(ErrorCode.TOKEN_NOT_EXIST, message);
    }

}
