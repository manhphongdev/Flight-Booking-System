package model;

/**
 *
 * @author manhphong
 */
public class Role {

    private Long roleId;
    private String roleName;
    private String description;

    public Role() {
    }

    public Role(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    public Role(Long roleId, String roleName, String description) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.description = description;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoleEntity{" + "roleId=" + roleId + ", roleName=" + roleName + ", description=" + description + '}';
    }
}
