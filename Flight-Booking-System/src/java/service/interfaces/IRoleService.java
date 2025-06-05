package service.interfaces;

import java.util.List;
import model.Role;

/**
 *
 * @author manhphong
 */
public interface IRoleService {
    
    public boolean addRole(Role role);

    public boolean updateByRoleName(Role entity, String role);

    public List<Role> getAllRole();

    public boolean deleteRoleById(Long id);
    
    public boolean addRoleHasPermission(String roleName, String permission);
}
