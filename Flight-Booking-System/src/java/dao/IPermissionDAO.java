package dao;

import java.util.Optional;
import model.PermissionEntity;

/**
 *
 * @author manhphong
 */
public interface IPermissionDAO extends IBaseDAO<PermissionEntity>{

    /**
     * update name of permission
     * @param entity
     * @param newEntity 
     */
    public boolean updateByPermissionName(PermissionEntity entity, PermissionEntity newEntity);
    
    /**
     * check a permission is exists in database
     * @param permissionName
     * @return 
     */
    public boolean isPermissionNameExists(String permissionName);
    
    Optional<PermissionEntity> findByName(String permissionName);
}
