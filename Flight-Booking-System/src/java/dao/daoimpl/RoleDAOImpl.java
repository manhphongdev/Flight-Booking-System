package dao.daoimpl;

import dao.IRoleDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import model.RoleEntity;
import utils.DBContext;

/**
 *
 * @author manhphong
 */
public class RoleDAOImpl implements IRoleDAO {

    private DBContext dbContext;

    public RoleDAOImpl() {
    }

    public RoleEntity roleMapper(ResultSet rs) throws SQLException {
        return new RoleEntity(rs.getLong("role_id"), rs.getString("role_name"), rs.getString("description"));
    }

    @Override
    public Optional<RoleEntity> findByID(Long id) {
        RoleEntity role = null;
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
    public List<RoleEntity> findAll() {
        List<RoleEntity> roles = new LinkedList<>();

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
    public Long insert(RoleEntity entity) {
        String sql = "INSERT INTO [Role] (role_name, description) VALUES (?, ?)";
        Connection conn = null;
        try {
            conn = DBContext.getConn();
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, entity.getRoleName());
                ps.setString(2, entity.getDescription());

                if (ps.executeUpdate() == 1) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            entity.setRoleId(rs.getLong(1));
                        }
                    }
                }
            }

            conn.commit(); // If not error, commit 
            return entity.getRoleId();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); 
                } catch (SQLException rollbackEx) {
                    System.err.println("Rollback failed: " + rollbackEx.getMessage());
                }
            }
            System.err.println("Insert failed: " + e.getMessage());
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Reset auto-commit
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public boolean updateByRoleName(RoleEntity entity, RoleEntity newRole) {
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
    public boolean updateByID(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
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
    public List<RoleEntity> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        RoleDAOImpl dao = new RoleDAOImpl();

        List<RoleEntity> lists = dao.findAll();

        for (RoleEntity list : lists) {
            System.out.println(list.toString());
        }
    }

    @Override
    public Optional<RoleEntity> findByName(String roleName) {
        RoleEntity role = null;
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

}
