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
public class UserInActiveException extends ServiceException{

    public UserInActiveException( String message) {
        super(ErrorCode.IN_ACTICE_USER, message);
    }

}
