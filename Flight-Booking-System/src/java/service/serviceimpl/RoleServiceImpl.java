package service.serviceimpl;

import dao.IPermissionDAO;
import dao.IRoleDAO;
import dao.daoimpl.PermissionDAOImpl;
import dao.daoimpl.RoleDAOImpl;
import java.util.List;
import java.util.Optional;
import model.PermissionEntity;
import model.RoleEntity;
import service.IRoleService;

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
    public boolean addRole(RoleEntity role) {
        Optional<RoleEntity> check = roleDAO.findByName(role.getRoleName());
        if (!check.isEmpty()) {
            return false;
        }
        roleDAO.insert(role);
        return true;
    }

    @Override
    public boolean updateByRoleName(RoleEntity newEntity, String roleName) {
        Optional<RoleEntity> entity = roleDAO.findByName(roleName);
        if (entity.isEmpty()) {
            return false;
        }
        RoleEntity roleEntity = entity.get();
        roleDAO.updateByRoleName(roleEntity, newEntity);
        return true;
    }

    @Override
    public List<RoleEntity> getAllRole() {
        return roleDAO.findAll();
    }

    @Override
    public boolean deleteRoleById(Long id) {
        return roleDAO.deleteByID(id);
    }

    @Override
    public boolean addRoleHasPermission(String roleName, String permissionName) {
        Optional<RoleEntity> role = roleDAO.findByName(roleName);
        Optional<PermissionEntity> permission = permissionDAO.findByName(permissionName);
        
        if(role.isPresent() && permission.isPresent()){
            roleDAO.saveRoleHasPermission(role.get(),permission.get());
            return true;
        }
        else{
            return false;
        }
    }
}
