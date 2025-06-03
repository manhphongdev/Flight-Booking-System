package dao;

import java.util.Optional;
import model.UserEntity;

/**
 *
 * @author manhphong
 */
public interface IUserDAO extends IBaseDAO<UserEntity> {

    /* find a user by email
     * @param email
     * @return a userEntity obejct
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * find user by email + password + role
     *
     * @param email
     * @param password
     * @param role
     * @return
     */
    Optional<UserEntity> findByEmailAndPasswordAndRole(String email, String password, String role);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    String getUserRole(UserEntity user);
    
    void addUserHasRole(UserEntity user);
}
