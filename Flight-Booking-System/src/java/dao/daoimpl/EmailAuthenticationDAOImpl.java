package dao.daoimpl;

import dao.IEmailAuthenticationDAO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Optional;
import model.EmailAuthentication;
import utils.DBContext;

/**
 *
 * @author manhphong
 */
public class EmailAuthenticationDAOImpl implements IEmailAuthenticationDAO {

    @Override
    public Long insertVerify(Long userID, String token, LocalDateTime expiredAt, String status) {
        String sql = "Insert into [Email_Authentication] values(?, ?, ?, ?)";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, userID);
            ps.setString(2, token);
            ps.setString(4, status);
            ps.setTimestamp(3, Timestamp.valueOf(expiredAt));
            if (ps.executeUpdate() == 1) {
                try {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        return rs.getLong(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return ps.getGeneratedKeys().getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateEmailAuthentication(Long id, EmailAuthentication entity) {
        String sql = "UPDATE [Email_Authentication] SET [status] = ?, [token] = ?, [expired_at] = ? WHERE id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getStatus());
            ps.setString(2, entity.getToken());
            ps.setTimestamp(3, Timestamp.valueOf(entity.getExpiredAt()));
            ps.setLong(4, id);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0; 

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Optional<EmailAuthentication> getEmailAuthencication(Long userId) {
        String sql = "SELECT * FROM [Email_Authentication] WHERE user_id = ?";
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new EmailAuthentication(
                            rs.getLong("id"),
                            userId,
                            rs.getString("token"),
                            rs.getTimestamp("expired_at").toLocalDateTime(),
                            rs.getString("status")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
