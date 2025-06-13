package service.serviceimpl;

import dao.interfaces.IPermissionDAO;
import dao.interfaces.IRoleDAO;
import dao.daoimpl.PermissionDAOImpl;
import dao.daoimpl.RoleDAOImpl;
import java.util.List;
import java.util.Optional;
import model.Permission;
import model.Role;
import service.interfaces.IRoleService;

/**
 *
 * @author manhphong
 */
public class RoleServiceImpl implements IRoleService {

    private IRoleDAO roleDAO;
    private IPermissionDAO permissionDAO;

    public RoleServiceImpl() {
        this.roleDAO = new RoleDAOImpl();
        this.permissionDAO = new PermissionDAOImpl();
    }

    @Override
    public boolean addRole(Role role) {
        Optional<Role> check = roleDAO.findByName(role.getRoleName());
        if (!check.isEmpty()) {
            return false;
        }
        roleDAO.insert(role);
        return true;
    }

    @Override
    public boolean updateByRoleName(Role newEntity, String roleName) {
        Optional<Role> entity = roleDAO.findByName(roleName);
        if (entity.isEmpty()) {
            return false;
        }
        Role roleEntity = entity.get();
        roleDAO.updateByRoleName(roleEntity, newEntity);
        return true;
    }

    @Override
    public List<Role> getAllRole() {
        return roleDAO.findAll();
    }

    @Override
    public boolean deleteRoleById(Long id) {
        return roleDAO.deleteByID(id);
    }

    @Override
    public boolean addRoleHasPermission(String roleName, String permissionName) {
        Optional<Role> role = roleDAO.findByName(roleName);
        Optional<Permission> permission = permissionDAO.findByName(permissionName);
        
        if(role.isPresent() && permission.isPresent()){
            roleDAO.saveRoleHasPermission(role.get(),permission.get());
            return true;
        }
        else{
            return false;
        }
    }
    
    
    public static void main(String[] args) {
        RoleServiceImpl roles = new RoleServiceImpl();
        
        List<Role> list = roles.getAllRole();
        for (Role role : list) {
            System.out.println(role.toString());
        }
    }
}
