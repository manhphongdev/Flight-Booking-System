package service.interfaces;

import java.util.List;
import model.Permission;

/**
 *
 * @author manhphong
 */
public interface IPermissionService {

    boolean addPermission(Permission permission);

    public boolean updateByPermissionName(Permission entity, String permission);

    public List<Permission> getAllPermission();

    public boolean deletePermissionById(Long id);
    
    List<String> getPermissionOfRole(String roleName);
    
    boolean deletePermissionOfRole(String roleName, String permissionName);
}
