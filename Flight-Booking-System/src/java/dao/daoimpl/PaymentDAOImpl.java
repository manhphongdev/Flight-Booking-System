package dao.daoimpl;

import dao.IPaymentDAO;
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
import model.Payment;
import utils.DBContext;

/**
 * 
 * @author Administrator
 * @version 1.0
 */
public class PaymentDAOImpl implements IPaymentDAO {
    
    private static final Logger logger = Logger.getLogger(PaymentDAOImpl.class.getName());

    @Override
    public Optional<Payment> findByID(Long id) {
        String sql = "SELECT * FROM [Payment] WHERE payment_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(paymentMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding payment by ID: {0}", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM [Payment]";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    payments.add(paymentMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding all payments: {0}", e.getMessage());
        }
        return payments;
    }

    @Override
    public Long insert(Payment payment) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO [Payment] (booking_id, amount, payment_method, status, created_at) ");
        sql.append("VALUES (?, ?, ?, ?, ?)");
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setLong(1, payment.getBookingId());
            ps.setDouble(2, payment.getAmount().valueOf(0));
            ps.setString(3, payment.getPaymentMethod());
            ps.setString(4, payment.getStatus());
            ps.setTimestamp(5, Timestamp.valueOf(payment.getCreatedAt()));
            
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        payment.setPaymentId(rs.getLong(1));
                        return payment.getPaymentId();
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "insert failed: {0}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateByID(Long id, Payment payment) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE [Payment] SET booking_id = ?, amount = ?, payment_method = ?, status = ? ");
        sql.append("WHERE payment_id = ?");
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            
            ps.setLong(1, payment.getBookingId());
            ps.setDouble(2, payment.getAmount());
            ps.setString(3, payment.getPaymentMethod());
            ps.setString(4, payment.getStatus());
            ps.setLong(5, id);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "update failed: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByID(Long id) {
        String sql = "DELETE FROM [Payment] WHERE payment_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "delete failed {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<Payment> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Payment> findByBookingId(Long bookingId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM [Payment] WHERE booking_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, bookingId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    payments.add(paymentMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding payments by booking ID: {0}", e.getMessage());
        }
        return payments;
    }

    @Override
    public List<Payment> findByStatus(String status) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM [Payment] WHERE status = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    payments.add(paymentMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding payments by status: {0}", e.getMessage());
        }
        return payments;
    }

    @Override
    public List<Payment> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM [Payment] WHERE created_at BETWEEN ? AND ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setTimestamp(1, Timestamp.valueOf(startDate));
            ps.setTimestamp(2, Timestamp.valueOf(endDate));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    payments.add(paymentMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding payments by date range: {0}", e.getMessage());
        }
        return payments;
    }

    @Override
    public boolean updateStatus(Long paymentId, String status) {
        String sql = "UPDATE [Payment] SET status = ? WHERE payment_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ps.setLong(2, paymentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error update failed: {0}", e.getMessage());
            return false;
        }
    }

    private Payment paymentMapper(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentId(rs.getLong("payment_id"));
        payment.setBookingId(rs.getLong("booking_id"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setPaymentMethod(rs.getString("payment_method"));
        payment.setStatus(rs.getString("status"));
        payment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return payment;
    }
} 