package dao;

import java.util.List;
import java.util.Optional;
import model.Permission;
import model.Role;

/**
 *
 * @author manhphong
 */
public interface IRoleDAO  extends IBaseDAO<Role>{
    /**
     * update name of user role
     * @param entity
     * @param newRole 
     */
    public boolean updateByRoleName(Role entity, Role newRole);
    /**
     * find T by id
     * @param roleName
     * @return T object if id exist else return null
     */
    Optional<Role> findByName(String roleName);

    public void saveRoleHasPermission(Role get, Permission get0);
    
}
