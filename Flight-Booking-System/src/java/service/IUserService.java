package service;

import model.UserEntity;

/**
 *
 * @author manhphong
 */
public interface IUserService {

    boolean register(UserEntity user);
    
    UserEntity login(String email, String password);
    
    String getUserRole(UserEntity user);
    
}
