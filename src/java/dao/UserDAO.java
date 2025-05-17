

package dao;

import java.util.Optional;
import model.UserEntity;

public interface UserDAO extends BaseDAO<UserEntity>{
    /**
     * find a user by email
     * @param email
     * @return a userEntity obejct
     */
    Optional<UserEntity> findByEmail(String email);
    
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
