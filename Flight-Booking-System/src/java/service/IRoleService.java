package service;

import java.util.List;
import model.RoleEntity;

/**
 *
 * @author manhphong
 */
public interface IRoleService {
    
    public boolean addRole(RoleEntity role);

    public boolean updateByRoleName(RoleEntity entity, String role);

    public List<RoleEntity> getAllRole();

    public boolean deleteRoleById(Long id);
    
    public boolean addRoleHasPermission(String roleName, String permission);
}
