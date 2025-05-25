package dao.daoimpl;

import dao.IUserDAO;
import enums.Gender;
import enums.UserStatus;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserEntity;
import utils.DBContext;

public class UserDAOImpl implements IUserDAO {

    private DBContext dbContext;
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
        UserEntity user = null;
        String sql = "SELECT * FROM [Users] WHERE user_id = ?";

        try (Connection conn = dbContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = userMapper(rs);
                    return Optional.of(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        Connection conn = dbContext.getConn();
        try {
            // Step 2: create statement object
            String sql = "INSERT INTO [Users] (email, password, first_name, "
                    + "last_name, created_at, updated_at, last_login_at, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFirstName());
            ps.setString(4, u.getLastName());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, u.getStatus().toString());

            logger.info("before ins userDao: " + u.toString());
            // Step 3: execute SQL statement
            if (ps.executeUpdate() == 1) { // executeUpdate tra ve so luong hang bi anh huong
                logger.info("after ins userDao: " + u.toString());
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    u.setUserId(rs.getLong(1));
                }
            }
            // Step 4: close connection
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error Code: " + ex.getErrorCode());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return u.getUserId();
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        String sql = "select * from [Users] where email =?";
        try (Connection conn = dbContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserEntity user = userMapper(rs);
                rs.close();
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

        try (Connection conn = dbContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, role);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return Optional.of(userMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<UserEntity> findAll() {
        String sql = "select * from [Users]";
        List<UserEntity> users = null;
        try(Connection conn = dbContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)){
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                users.add(userMapper(rs));
            }
        }
        catch(SQLException e ){
            e.printStackTrace();
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
 
}
