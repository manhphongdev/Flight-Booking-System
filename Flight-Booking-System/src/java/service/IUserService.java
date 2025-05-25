
package service;

import model.UserEntity;

/**
 *
 * @author manhphong
 */
public interface IUserService {

    Long register(UserEntity user);
    
    boolean loginAdmin(String email, String password);
    
    
}
