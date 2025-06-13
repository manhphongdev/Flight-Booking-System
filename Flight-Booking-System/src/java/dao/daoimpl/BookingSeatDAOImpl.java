package dao.daoimpl;

import dao.interfaces.IBookingSeatDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BookingSeat;
import utils.DBContext;
/**
 * 
 * @author Administrator
 * @version 1.0
 */
public class BookingSeatDAOImpl implements IBookingSeatDAO {
    
    private static final Logger logger = Logger.getLogger(BookingSeatDAOImpl.class.getName());

    @Override
    public Optional<BookingSeat> findByID(Long id) {
        String sql = "SELECT * FROM [BookingSeat] WHERE booking_id = ? AND seat_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            ps.setLong(2, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(bookingSeatMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error finding booking seat by ID: {0}", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<BookingSeat> findAll() {
        List<BookingSeat> bookingSeats = new ArrayList<>();
        String sql = "SELECT * FROM [BookingSeat]";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bookingSeats.add(bookingSeatMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error finding all booking seats: {0}", e.getMessage());
        }
        return bookingSeats;
    }

    @Override
    public Long insert(BookingSeat bookingSeat) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO [BookingSeat] (booking_id, seat_id, passenger_id, created_at) ");
        sql.append("VALUES (?, ?, ?, ?)");
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            
            ps.setLong(1, bookingSeat.getBookingId());
            ps.setLong(2, bookingSeat.getSeatId());
            ps.setLong(3, bookingSeat.getPassengerId());
            ps.setTimestamp(4, new Timestamp(bookingSeat.getCreatedAt().getTime));
            
            if (ps.executeUpdate() == 1) {
                return bookingSeat.getBookingId();
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error inserting booking seat: {0}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateByID(Long id, BookingSeat bookingSeat) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE [BookingSeat] SET booking_id = ?, seat_id = ?, passenger_id = ? ");
        sql.append("WHERE booking_id = ? AND seat_id = ?");
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            
            ps.setLong(1, bookingSeat.getBookingId());
            ps.setLong(2, bookingSeat.getSeatId());
            ps.setLong(3, bookingSeat.getPassengerId());
            ps.setLong(4, id);
            ps.setLong(5, id);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error updating booking seat: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByID(Long id) {
        String sql = "DELETE FROM [BookingSeat] WHERE booking_id = ? AND seat_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            ps.setLong(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error deleting booking seat: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<BookingSeat> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BookingSeat> findByBookingId(Long bookingId) {
        List<BookingSeat> bookingSeats = new ArrayList<>();
        String sql = "SELECT * FROM [BookingSeat] WHERE booking_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, bookingId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bookingSeats.add(bookingSeatMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error finding booking seats by booking ID: {0}", e.getMessage());
        }
        return bookingSeats;
    }

    @Override
    public List<BookingSeat> findBySeatId(Long seatId) {
        List<BookingSeat> bookingSeats = new ArrayList<>();
        String sql = "SELECT * FROM [BookingSeat] WHERE seat_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, seatId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bookingSeats.add(bookingSeatMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error finding booking seats by seat ID: {0}", e.getMessage());
        }
        return bookingSeats;
    }

    @Override
    public List<BookingSeat> findByPassengerId(Long passengerId) {
        List<BookingSeat> bookingSeats = new ArrayList<>();
        String sql = "SELECT * FROM [BookingSeat] WHERE passenger_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, passengerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bookingSeats.add(bookingSeatMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error finding booking seats by passenger ID: {0}", e.getMessage());
        }
        return bookingSeats;
    }

    @Override
    public boolean deleteByBookingId(Long bookingId) {
        String sql = "DELETE FROM [BookingSeat] WHERE booking_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, bookingId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting booking seats by booking ID: {0}", e.getMessage());
            return false;
        }
    }

    private BookingSeat bookingSeatMapper(ResultSet rs) throws SQLException {
        BookingSeat bookingSeat = new BookingSeat();
        bookingSeat.setBookingId(rs.getLong("booking_id"));
        bookingSeat.setSeatId(rs.getLong("seat_id"));
        bookingSeat.setPassengerId(rs.getLong("passenger_id"));
        bookingSeat.setCreatedAt(rs.getTimestamp("created_at"));
        return bookingSeat;
    }
} 