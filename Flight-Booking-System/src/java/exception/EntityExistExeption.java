/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package exception;

import enums.ErrorCode;

/**
 *
 * @author manhphong
 * @version 1.0
 */
public class EntityExistExeption extends ServiceException{
    public EntityExistExeption( String message) {
        super(ErrorCode.ENTITY_IS_EXIST, message);
    }
}
