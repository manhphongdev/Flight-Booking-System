package service.serviceimpl;

import dao.IRoleDAO;
import dao.daoimpl.RoleDAOImpl;
import java.util.List;
import java.util.Optional;
import model.RoleEntity;
import service.IRoleService;

/**
 *
 * @author manhphong
 */
public class RoleSerrviceImpl implements IRoleService {

    private IRoleDAO roleDAO;

    public RoleSerrviceImpl() {
        this.roleDAO = new RoleDAOImpl();
    }

    @Override
    public Long addRole(RoleEntity role) {
        Optional<RoleEntity> check = roleDAO.findByName(role.getRoleName());
        if (!check.isEmpty()) {
            return null;
        }
        return roleDAO.insert(role);
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
}
