package dao.daoimpl;

import dao.IUserDAO;
import enums.UserStatus;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserEntity;
import utils.DBContext;

public class UserDAOImpl implements IUserDAO {
    
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
    
    public UserDAOImpl() {
    }
    
    @Override
    /**
     * Step 1: create a connection to database Step 2: Create a
     * PreparedStatement Step 3: execute PreparedStatement Step 4: Close
     * connection
     */
    public Optional<UserEntity> findByID(Long id) {
        String sql = "SELECT * FROM [Users] WHERE user_id = ?";
        
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserEntity user = userMapper(rs);
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return Optional.empty();
    }
    
    public UserEntity userMapper(ResultSet rs) throws SQLException {
        return new UserEntity(
                rs.getLong("user_id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null,
                rs.getTimestamp("last_login_at") != null ? rs.getTimestamp("last_login_at").toLocalDateTime() : null,
                rs.getString("status") != null ? UserStatus.valueOf(rs.getString("status")) : null
        );
    }
    
    @Override
    public Long insert(UserEntity u) {
        // Step 1: create a connection to database
        Connection conn = DBContext.getConn();
        try {
            // Step 2: create statement object
            String sql = "INSERT INTO [Users] (email, password, first_name, "
                    + "last_name, created_at, updated_at, last_login_at, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFirstName());
            ps.setString(4, u.getLastName());
            ps.setTimestamp(5, Timestamp.valueOf(u.getCreatedAt()));
            ps.setTimestamp(6, Timestamp.valueOf(u.getUpdatedAt()));
            ps.setTimestamp(7, Timestamp.valueOf(u.getLastLoginedAt()));
            ps.setString(8, u.getStatus().toString());
            
            logger.log(Level.INFO, "before ins userDao: {0}", u.toString());
            // Step 3: execute SQL statement
            if (ps.executeUpdate() == 1) { // executeUpdate tra ve so luong hang bi anh huong
                logger.log(Level.INFO, "after ins userDao: {0}", u.toString());
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    u.setUserId(rs.getLong(1));
                    return u.getUserId();
                }
            }
            // Step 4: close connection
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error Code: {0}", ex.getErrorCode());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                logger.info(ex.getMessage());
            }
        }
        return null;
    }
    
    @Override
    public Optional<UserEntity> findByEmail(String email) {
        String sql = "select * from [Users] where email =?";
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserEntity user = userMapper(rs);
                rs.close();
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return Optional.empty();
    }
    
    @Override
    public Optional<UserEntity> findByEmailAndPasswordAndRole(String email, String password, String role) {
        
        StringBuilder sb = new StringBuilder();
        sb.append("select * from [Users] u ");
        sb.append("join [User_Role] ur on u.user_id = ur.user_id ");
        sb.append("join [Role] r on r.role_id = ur.role_id ");
        sb.append("where email = ? and password = ? and r.role_name = ?");
        
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, role);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return Optional.of(userMapper(rs));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return Optional.empty();
    }
    
    @Override
    public List<UserEntity> findAll() {
        String sql = "select * from [Users]";
        List<UserEntity> users = null;
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(userMapper(rs));
            }
        } catch (SQLException e) {
           logger.info(e.getMessage());
        }
        return users;
    }
    
    @Override
    public List<UserEntity> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public boolean updateByID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public boolean deleteByID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Optional<UserEntity> findByEmailAndPassword(String email, String password) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from [Users] u ");
        sb.append("where email = ? and password = ?");
        
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return Optional.of(userMapper(rs));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return Optional.empty();
    }
    
    @Override
    public String getUserRole(UserEntity user) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT r.role_name FROM [Role] r ");
        sb.append("JOIN [User_Has_Role] ur ON r.role_id = ur.role_id ");
        sb.append("WHERE ur.user_id = ?");
        
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            
            ps.setLong(1, user.getUserId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("role_name");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }
    
    @Override
    public void addUserHasRole(UserEntity user) {
        StringBuilder sb = new StringBuilder("Insert into User_Has_Role ");
        sb.append("values(?, 2)");
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setLong(1, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
