package dao.daoimpl;

import dao.interfaces.IUserDAO;
import enums.UserStatus;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import utils.DBContext;

public class UserDAOImpl implements IUserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());

    @Override
    /**
     * Step 1: create a connection to database Step 2: Create a
     * PreparedStatement Step 3: execute PreparedStatement Step 4: Close
     * connection
     */
    public Optional<User> findByID(Long id) {
        String sql = "SELECT * FROM [Users] WHERE user_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = userMapper(rs);
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return Optional.empty();
    }

    public User userMapper(ResultSet rs) throws SQLException {
        return new User(
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
    public Long insert(User u) {
        try (Connection conn = DBContext.getConn();) {
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
            if (ps.executeUpdate() == 1) {
                logger.log(Level.INFO, "after ins userDao: {0}", u.toString());
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    u.setUserId(rs.getLong(1));
                    return u.getUserId();
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error Code: {0}", ex.getErrorCode());
        }
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "select * from [Users] where email =?";
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = userMapper(rs);
                rs.close();
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPasswordAndRole(String email, String password, String role) {

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
    public List<User> findAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("select u.user_id,u.email, u.first_name, u.last_name, u.last_login_at, u.status, r.role_name from [Users] u ");
        sb.append("left join [User_Has_Role] ur on u.user_id = ur.user_id ");
        sb.append("left join [Role] r on r.role_id = ur.role_id ");

        List<User> users = new ArrayList<>();
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getLong("user_id"),
                        rs.getString("email"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getTimestamp("last_login_at").toLocalDateTime(),
                        UserStatus.valueOf(rs.getString("status")),
                        rs.getString("role_name"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return users;
    }

    @Override
    public List<User> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean updateByID(Long id, User entity) {
        String sql = "Update [Users] set [email] = ?, [password] = ?, [first_name] = ?, [last_name] = ?, [status] = ?, [updated_at] = ? WHERE user_id = ?";
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getFirstName());
            ps.setString(4, entity.getLastName());
            ps.setString(5, entity.getStatus().toString());
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(7, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating user: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByID(Long id) {
        String sql = "DELETE from [USERS] where user_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
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
    public String getUserRole(User user) {
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
    public void addUserHasRole(User user) {
        StringBuilder sb = new StringBuilder("Insert into User_Has_Role ");
        sb.append("values(?, (select role_id from Role where role_name = ?))");
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setLong(1, user.getUserId());
            ps.setString(2, user.getUserRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public boolean updateUserHasRole(Long id, String role) {
        StringBuilder sb = new StringBuilder("Update [User_Has_Role] ");
        sb.append("set role_id = (select role_id from [Role] where role_name =?) where user_id = ?");
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setString(1, role);
            ps.setLong(2, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User userMapperForSearch(ResultSet rs) throws SQLException {

        User user = new User(rs.getLong("user_id"),
                rs.getString("email"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getTimestamp("last_login_at") != null ? rs.getTimestamp("last_login_at").toLocalDateTime() : null,
                rs.getString("status") != null ? UserStatus.valueOf(rs.getString("status")) : null);
        String userRole = getUserRole(user);
        user.setUserRole(userRole);
        return user;
    }

    @Override
    public List<User> searchByName(String name) {
        List<User> users = new ArrayList<>();

        String sql = "Select * from [Users] where last_name + ' ' + first_name like ?";
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(userMapperForSearch(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> searchByEmail(String email) {
        List<User> users = new ArrayList<>();

        String sql = "Select * from [Users] where email like ?";
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + email + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(userMapperForSearch(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> searchByNameAndRole(String name, String roleName) {
        List<User> users = new ArrayList<>();

        StringBuilder sb = new StringBuilder("Select * from [Users] u ");
        sb.append("JOIN [User_Has_Role] ur ON u.user_id = ur.user_id ");
        sb.append("JOIN Role r on r.role_id = ur.role_id and role_name =? ");
        sb.append("where last_name + ' ' + first_name like ?");
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setString(1, roleName);

            ps.setString(2, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(userMapperForSearch(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> searchByEmailAndRole(String email, String role) {
        List<User> users = new ArrayList<>();

        StringBuilder sb = new StringBuilder("Select * from [Users] u ");
        sb.append("JOIN [User_Has_Role] ur ON u.user_id = ur.user_id ");
        sb.append("JOIN Role r on r.role_id = ur.role_id and role_name =? ");
        sb.append("where email like ?");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setString(1, role);
            ps.setString(2, "%" + email + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(userMapperForSearch(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void main(String[] args) {
        UserDAOImpl dao = new UserDAOImpl();
        List<User> list = dao.searchByEmailAndRole("", "ADMIN");

        for (User user : list) {
            System.out.println(user.toString());
        }
    }

}
