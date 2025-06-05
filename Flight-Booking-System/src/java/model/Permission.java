package model;

/**
 *
 * @author manhphong
 */
public class Permission {

    private Long permissionId;
    private String permissonName;
    private String description;

    public Permission() {
    }

    public Permission(String permissonName, String description) {
        this.permissonName = permissonName;
        this.description = description;
    }

    public Permission(Long permissionId, String permissonName, String description) {
        this.permissionId = permissionId;
        this.permissonName = permissonName;
        this.description = description;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissonName() {
        return permissonName;
    }

    public void setPermissonName(String permissonName) {
        this.permissonName = permissonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PermissionEntity{" + "permissionId=" + permissionId + ", permissonName=" + permissonName + ", description=" + description + '}';
    }
}
