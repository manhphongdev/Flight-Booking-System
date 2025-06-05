package service.interfaces;

import java.util.List;
import model.User;

/**
 *
 * @author manhphong
 */
public interface IUserService {

    boolean register(User user);
    
    User login(String email, String password);
    
    String getUserRole(User user);
    
    void updateUser(User user, Long id);
    
    List<User> getAll();
    
    boolean addUser(User user);
    
    void deleteUser(Long id);
    
    void adminUpdateUser(User user);
    
    List<User> searchByNameASC(String email);
    List<User> searchByNameDESC(String email);
    List<User> searchByEmailASC(String email);
    List<User> searchByEmailDESC(String email);
    
    
    List<User> searchByNameAndRoleAndOrderByNameASC(String name, String roleName);
    List<User> searchByNameAndRoleAndOrderByNameDESC(String name, String roleName);
    List<User> searchByEmailAndRoleAndOrderByNameASC(String email, String roleName);
    List<User> searchByEmailAndRoleAndOrderByNameDESC(String email, String roleName);
}
