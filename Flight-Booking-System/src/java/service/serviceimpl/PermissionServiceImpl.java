package service.serviceimpl;

import dao.IPermissionDAO;
import dao.daoimpl.PermissionDAOImpl;
import java.util.List;
import java.util.Optional;
import model.PermissionEntity;
import service.IPermissionService;

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
    public Long addPermission(PermissionEntity permission) {
        Optional<PermissionEntity> entity = dao.findByID(permission.getPermissionId());
        if (!entity.isEmpty()) {
            return null;
        }
        return dao.insert(permission);
    }

    @Override
    public boolean updateByPermissionName(PermissionEntity newEntity, String permission) {
        Optional<PermissionEntity> entity = dao.findByName(permission);
        if (entity.isEmpty()) {
            return false;
        }
        PermissionEntity permissionEntity = entity.get();
        dao.updateByPermissionName(permissionEntity, newEntity);
        return true;
    }

    @Override
    public List<PermissionEntity> getAllPermission() {
        return dao.findAll();
    }

    @Override
    public boolean deletePermissionById(Long id) {
        return dao.deleteByID(id);
    }
}
