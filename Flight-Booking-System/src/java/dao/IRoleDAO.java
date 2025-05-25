package dao;

import java.util.Optional;
import model.RoleEntity;

/**
 *
 * @author manhphong
 */
public interface IRoleDAO  extends IBaseDAO<RoleEntity>{
    /**
     * update name of user role
     * @param entity
     * @param newRole 
     */
    public boolean updateByRoleName(RoleEntity entity, RoleEntity newRole);
    /**
     * find T by id
     * @param roleName
     * @return T object if id exist else return null
     */
    Optional<RoleEntity> findByName(String roleName);
}
