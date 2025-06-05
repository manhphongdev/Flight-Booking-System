package dao.daoimpl;

import dao.IRoleDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import model.Permission;
import model.Role;
import utils.DBContext;

/**
 *
 * @author manhphong
 */
public class RoleDAOImpl implements IRoleDAO {

    private DBContext dbContext;

    public RoleDAOImpl() {
    }

    public Role roleMapper(ResultSet rs) throws SQLException {
        return new Role(rs.getLong("role_id"), rs.getString("role_name"), rs.getString("description"));
    }

    @Override
    public Optional<Role> findByID(Long id) {
        Role role = null;
        String sql = "SELECT * FROM [Role] WHERE role_id = ?";

        try (Connection conn = dbContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    role = roleMapper(rs);
                    return Optional.of(role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new LinkedList<>();

        String sql = "select * from [Role]";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                roles.add(roleMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public Long insert(Role entity) {
        String sql = "INSERT INTO [Role] (role_name, description) VALUES (?, ?)";
        Connection conn = null;
        try {
            conn = DBContext.getConn();
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, entity.getRoleName());
                ps.setString(2, entity.getDescription());

                if (ps.executeUpdate() == 1) {
                    return ps.getGeneratedKeys().getLong(1);
                }
            }

            conn.commit(); // If not error, commit 
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Reset auto-commit
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public boolean updateByRoleName(Role entity, Role newRole) {
        StringBuilder sb = new StringBuilder();
        sb.append("update [Role] set role_name = ?, description = ? ");
        sb.append("where role_name = ?");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setString(1, newRole.getRoleName());
            ps.setString(2, newRole.getDescription());
            ps.setString(3, entity.getRoleName());

            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByID(Long id) {
        String sql = "delete from [Role] where role_id = ?";
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Role> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        RoleDAOImpl dao = new RoleDAOImpl();

        List<Role> lists = dao.findAll();

        for (Role list : lists) {
            System.out.println(list.toString());
        }
    }

    @Override
    public Optional<Role> findByName(String roleName) {
        Role role = null;
        String sql = "SELECT * FROM [Role] WHERE role_name = ?";

        try (Connection conn = dbContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, roleName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    role = roleMapper(rs);
                    return Optional.of(role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void saveRoleHasPermission(Role get, Permission get0) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into Role_Has_Permission ");
        sb.append("values((select r.role_id from Role r where r.role_name = ?),");
        sb.append("(select p.permission_id from Permission p where p.permission_name = ?))");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setString(1, get.getRoleName());
            ps.setString(2, get0.getPermissonName());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateByID(Long id, Role entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
