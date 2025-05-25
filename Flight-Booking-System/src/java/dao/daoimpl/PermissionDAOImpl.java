package dao.daoimpl;

import dao.IPermissionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PermissionEntity;
import utils.DBContext;

/**
 *
 * @author manhphong
 */
public class PermissionDAOImpl implements IPermissionDAO {

    public PermissionEntity permissionMapper(ResultSet rs) throws Exception {
        return new PermissionEntity(rs.getLong("permission_id"), rs.getNString("permission_name"), rs.getNString("description"));
    }

    @Override
    public Optional<PermissionEntity> findByID(Long id) {
        PermissionEntity permission = null;
        String sql = "SELECT * FROM [Permission] WHERE permission_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    permission = permissionMapper(rs);
                    return Optional.of(permission);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<PermissionEntity> findAll() {
        List<PermissionEntity> permissions = new LinkedList<>();

        String sql = "select * from [Permission]";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                try {
                    permissions.add(permissionMapper(rs));
                } catch (Exception ex) {
                    Logger.getLogger(PermissionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permissions;
    }

    @Override
    public Long insert(PermissionEntity entity) {
        StringBuilder sb = new StringBuilder("insert into [Permission] (permission_name, description)");
        sb.append("values(?,?)");
        Connection conn = null;
        try {
            conn = DBContext.getConn();
            PreparedStatement ps = conn.prepareStatement(sb.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
            conn.setAutoCommit(false);

            ps.setString(1, entity.getPermissonName());
            ps.setString(2, entity.getDescription());

            if (ps.executeUpdate() == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    entity.setPermissionId(rs.getLong(1));
                }
            }
            conn.commit();
            return entity.getPermissionId();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); 
                } catch (SQLException rollbackEx) {
                    System.err.println("Rollback failed: " + rollbackEx.getMessage());
                }
            }
            System.err.println("Insert failed: " + e.getMessage());
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
        return null;
    }

    @Override
    public boolean updateByID(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteByID(Long id) {
        String sql = "delete from [Permission] where permission_id = ?";
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
    public List<PermissionEntity> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean updateByPermissionName(PermissionEntity entity, PermissionEntity newEntity) {

        StringBuilder sb = new StringBuilder();
        sb.append("update [Permission] set permission_name = ?, description = ? ");
        sb.append("where permission_name = ?");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setString(1, newEntity.getPermissonName());
            ps.setString(2, newEntity.getDescription());
            ps.setString(3, entity.getPermissonName());

            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isPermissionNameExists(String permissionName) {

        String sql = "select count(*) from [Permission] where permission_name = ?";
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, permissionName);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
        } catch (Exception ex) {
            Logger.getLogger(PermissionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Optional<PermissionEntity> findByName(String permissionName) {
        PermissionEntity role = null;
        String sql = "SELECT * FROM [Permission] WHERE permission_name = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, permissionName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    role = permissionMapper(rs);
                    return Optional.of(role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
