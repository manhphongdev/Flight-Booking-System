package service;

import java.util.List;
import model.PermissionEntity;

/**
 *
 * @author manhphong
 */
public interface IPermissionService {

    Long addPermission(PermissionEntity permission);

    public boolean updateByPermissionName(PermissionEntity entity, String permission);

    public List<PermissionEntity> getAllPermission();

    public boolean deletePermissionById(Long id);
}
