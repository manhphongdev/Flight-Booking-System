package dao.daoimpl;

import dao.interfaces.ICouponDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coupon;
import utils.DBContext;

/**
 * @author Administrator
 * @version 1.2
 */
public class CouponDAOImpl implements ICouponDAO {

    private static final Logger logger = Logger.getLogger(CouponDAOImpl.class.getName());

    @Override
    public Optional<Coupon> findByID(Long id) {
        String sql = "SELECT * FROM [Coupon] WHERE coupon_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(couponMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding coupon by ID {0}: {1}", new Object[]{id, e.getMessage()});
        }
        return Optional.empty();
    }

    @Override
    public List<Coupon> findAll() {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM [Coupon]";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    coupons.add(couponMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding all coupons: {0}", e.getMessage());
        }
        return coupons;
    }

    @Override
    public Long insert(Coupon coupon) {
        String sql = "INSERT INTO [Coupon] (coupon_code, discount_percentage, max_usage, start_date, end_date, status, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, coupon.getCouponCode());
            if (coupon.getDiscountPercentage() != null) {
                ps.setInt(2, coupon.getDiscountPercentage());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }
            if (coupon.getMaxUsage() != null) {
                ps.setInt(3, coupon.getMaxUsage());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }
            ps.setTimestamp(4, Timestamp.valueOf(coupon.getStartDate()));
            ps.setTimestamp(5, Timestamp.valueOf(coupon.getEndDate()));
            ps.setString(6, coupon.getStatus());
            ps.setTimestamp(7, Timestamp.valueOf(coupon.getCreatedAt() != null ? coupon.getCreatedAt() : LocalDateTime.now()));

            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        coupon.setCouponId(rs.getLong(1));
                        return coupon.getCouponId();
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting coupon with code {0}: {1}", new Object[]{coupon.getCouponCode(), e.getMessage()});
        }
        return null;
    }

    @Override
    public boolean updateByID(Long id, Coupon coupon) {
        String sql = "UPDATE [Coupon] SET coupon_code = ?, discount_percentage = ?, max_usage = ?, start_date = ?, end_date = ?, status = ? " +
                    "WHERE coupon_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, coupon.getCouponCode());
            if (coupon.getDiscountPercentage() != null) {
                ps.setInt(2, coupon.getDiscountPercentage());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }
            if (coupon.getMaxUsage() != null) {
                ps.setInt(3, coupon.getMaxUsage());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }
            ps.setTimestamp(4, Timestamp.valueOf(coupon.getStartDate()));
            ps.setTimestamp(5, Timestamp.valueOf(coupon.getEndDate()));
            ps.setString(6, coupon.getStatus());
            ps.setLong(7, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating coupon with ID {0}: {1}", new Object[]{id, e.getMessage()});
            return false;
        }
    }

    @Override
    public boolean deleteByID(Long id) {
        String sql = "DELETE FROM [Coupon] WHERE coupon_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting coupon with ID {0}: {1}", new Object[]{id, e.getMessage()});
            return false;
        }
    }

    @Override
    public List<Coupon> selectByCondition(String search, String status, String sortBy) {
        List<Coupon> coupons = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM [Coupon] WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // Search by coupon_code
        if (search != null && !search.trim().isEmpty()) {
            sql.append(" AND coupon_code LIKE ?");
            params.add("%" + search.trim() + "%");
        }

        // Filter by status
        if (status != null && !status.equals("all")) {
            sql.append(" AND status = ?");
            params.add(status);
        }

        // Sort by
        if (sortBy != null) {
            switch (sortBy) {
                case "couponCodeASC" -> sql.append(" ORDER BY coupon_code ASC");
                case "couponCodeDESC" -> sql.append(" ORDER BY coupon_code DESC");
                case "createdAtASC" -> sql.append(" ORDER BY created_at ASC");
                case "createdAtDESC" -> sql.append(" ORDER BY created_at DESC");
                default -> sql.append(" ORDER BY coupon_id ASC");
            }
        }

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    coupons.add(couponMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error selecting coupons by condition: {0}", e.getMessage());
        }
        return coupons;
    }

    @Override
    public List<Coupon> findByCode(String code) {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM [Coupon] WHERE coupon_code = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    coupons.add(couponMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding coupons by code {0}: {1}", new Object[]{code, e.getMessage()});
        }
        return coupons;
    }

    @Override
    public List<Coupon> findByStatus(String status) {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM [Coupon] WHERE status = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    coupons.add(couponMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding coupons by status {0}: {1}", new Object[]{status, e.getMessage()});
        }
        return coupons;
    }

    @Override
    public List<Coupon> findByEndDate(LocalDateTime endDate) {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM [Coupon] WHERE end_date = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(endDate));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    coupons.add(couponMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding coupons by end date {0}: {1}", new Object[]{endDate, e.getMessage()});
        }
        return coupons;
    }

    @Override
    public boolean updateStatus(Long couponId, String status) {
        String sql = "UPDATE [Coupon] SET status = ? WHERE coupon_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setLong(2, couponId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating status for coupon ID {0}: {1}", new Object[]{couponId, e.getMessage()});
            return false;
        }
    }

    private Coupon couponMapper(ResultSet rs) throws SQLException {
        Coupon coupon = new Coupon();
        coupon.setCouponId(rs.getLong("coupon_id"));
        coupon.setCouponCode(rs.getString("coupon_code"));
        int discountPercentage = rs.getInt("discount_percentage");
        coupon.setDiscountPercentage(rs.wasNull() ? null : discountPercentage);
        int maxUsage = rs.getInt("max_usage");
        coupon.setMaxUsage(rs.wasNull() ? null : maxUsage);
        int usedCount = rs.getInt("used_count");
        coupon.setUsedCount(rs.wasNull() ? null : usedCount);
        coupon.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
        coupon.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
        coupon.setStatus(rs.getString("status"));
        coupon.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return coupon;
    }

    @Override
    public List<Coupon> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}