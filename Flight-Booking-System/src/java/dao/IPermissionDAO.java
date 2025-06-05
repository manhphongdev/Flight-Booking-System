package dao;

import java.util.List;
import java.util.Optional;
import model.Permission;

/**
 *
 * @author manhphong
 */
public interface IPermissionDAO extends IBaseDAO<Permission> {

    /**
     * update name of permission
     *
     * @param entity
     * @param newEntity
     */
    public boolean updateByPermissionName(Permission entity, Permission newEntity);

    /**
     * check a permission is exists in database
     *
     * @param permissionName
     * @return
     */
    public boolean isPermissionNameExists(String permissionName);

    Optional<Permission> findByName(String permissionName);

    public List<String> findPermissionOfRole(String roleName);
    
    boolean deletePermissionOfRole(String roleName, String permissionName);

}
