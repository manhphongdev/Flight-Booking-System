package service.serviceimpl;

import dao.interfaces.IPermissionDAO;
import dao.daoimpl.PermissionDAOImpl;
import java.util.List;
import java.util.Optional;
import model.Permission;
import service.interfaces.IPermissionService;

/**
 *
 * @author manhphong
 */
public class PermissionServiceImpl implements IPermissionService {

    private IPermissionDAO dao;

    public PermissionServiceImpl() {
        this.dao = new PermissionDAOImpl();
    }

    @Override
    public boolean addPermission(Permission permission) {
        Optional<Permission> entity = dao.findByID(permission.getPermissionId());
        if (!entity.isEmpty()) {
            return false;
        }
        dao.insert(permission);
        return true;
    }

    @Override
    public boolean updateByPermissionName(Permission newEntity, String permission) {
        Optional<Permission> entity = dao.findByName(permission);
        if (entity.isEmpty()) {
            return false;
        }
        Permission permissionEntity = entity.get();
        dao.updateByPermissionName(permissionEntity, newEntity);
        return true;
    }

    @Override
    public List<Permission> getAllPermission() {
        return dao.findAll();
    }

    @Override
    public boolean deletePermissionById(Long id) {
        return dao.deleteByID(id);
    }

    @Override
    public List<String> getPermissionOfRole(String roleName) {
        return dao.findPermissionOfRole(roleName);
    }

    @Override
    public boolean deletePermissionOfRole(String roleName, String permissionName) {
        return dao.deletePermissionOfRole(roleName, permissionName);
    }
}
