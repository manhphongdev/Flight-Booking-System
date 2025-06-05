package dao.daoimpl;

import dao.ICouponDAO;
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
 * 
 * @author Administrator
 * @version 1.0
 */
public class CouponDAOImpl implements ICouponDAO {
    
    private static final Logger logger = Logger.getLogger(CouponDAOImpl.class.getName());

    @Override
    public Optional<Coupon> findByID(Long id) {
        String sql = "SELECT * FROM [Coupon] WHERE coupon_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(couponMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding coupon by ID: {0}", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Coupon> findAll() {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM [Coupon]";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
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
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO [Coupon] (code, discount_percent, expiry_date, status, created_at) ");
        sql.append("VALUES (?, ?, ?, ?, ?)");
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, coupon.getCode());
            ps.setDouble(2, coupon.getDiscountPercent());
            ps.setTimestamp(3, Timestamp.valueOf(coupon.getExpiryDate()));
            ps.setString(4, coupon.getStatus());
            ps.setTimestamp(5, Timestamp.valueOf(coupon.getCreatedAt()));
            
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        coupon.setCouponId(rs.getLong(1));
                        return coupon.getCouponId();
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting coupon: {0}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateByID(Long id, Coupon coupon) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE [Coupon] SET code = ?, discount_percent = ?, expiry_date = ?, status = ? ");
        sql.append("WHERE coupon_id = ?");
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            
            ps.setString(1, coupon.getCode());
            ps.setDouble(2, coupon.getDiscountPercent());
            ps.setTimestamp(3, Timestamp.valueOf(coupon.getExpiryDate()));
            ps.setString(4, coupon.getStatus());
            ps.setLong(5, id);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating coupon: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByID(Long id) {
        String sql = "DELETE FROM [Coupon] WHERE coupon_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting coupon: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<Coupon> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Coupon> findByCode(String code) {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM [Coupon] WHERE code = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    coupons.add(couponMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding coupons by code: {0}", e.getMessage());
        }
        return coupons;
    }

    @Override
    public List<Coupon> findByStatus(String status) {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM [Coupon] WHERE status = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    coupons.add(couponMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding coupons by status: {0}", e.getMessage());
        }
        return coupons;
    }

    @Override
    public List<Coupon> findByExpiryDate(LocalDateTime expiryDate) {
        List<Coupon> coupons = new ArrayList<>();
        String sql = "SELECT * FROM [Coupon] WHERE expiry_date = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setTimestamp(1, Timestamp.valueOf(expiryDate));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    coupons.add(couponMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding coupons by expiry date: {0}", e.getMessage());
        }
        return coupons;
    }

    @Override
    public boolean updateStatus(Long couponId, String status) {
        String sql = "UPDATE [Coupon] SET status = ? WHERE coupon_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ps.setLong(2, couponId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating coupon status: {0}", e.getMessage());
            return false;
        }
    }

    private Coupon couponMapper(ResultSet rs) throws SQLException {
        Coupon coupon = new Coupon();
        coupon.setCouponId(rs.getLong("coupon_id"));
        coupon.setCode(rs.getString("code"));
        coupon.setDiscountPercent(rs.getDouble("discount_percent"));
        coupon.setExpiryDate(rs.getTimestamp("expiry_date").toLocalDateTime());
        coupon.setStatus(rs.getString("status"));
        coupon.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return coupon;
    }
} 