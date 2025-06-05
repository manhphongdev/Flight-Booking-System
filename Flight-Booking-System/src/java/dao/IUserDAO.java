package dao;

import java.util.List;
import java.util.Optional;
import model.User;

/**
 *
 * @author manhphong
 */
public interface IUserDAO extends IBaseDAO<User> {

    /* find a user by email
     * @param email
     * @return a userEntity obejct
     */
    Optional<User> findByEmail(String email);

    /**
     * find user by email + password + role
     *
     * @param email
     * @param password
     * @param role
     * @return
     */
    Optional<User> findByEmailAndPasswordAndRole(String email, String password, String role);

    Optional<User> findByEmailAndPassword(String email, String password);

    String getUserRole(User user);
    
    void addUserHasRole(User user);
    
    //void updateUserRole(Long userId, Long role);
    
    boolean updateUserHasRole(Long id, String role);
    
    List<User> searchByName(String name);
    
    List<User> searchByEmail(String email);
    
    List<User> searchByNameAndRole(String name, String role);
    List<User> searchByEmailAndRole(String email, String role);
}
