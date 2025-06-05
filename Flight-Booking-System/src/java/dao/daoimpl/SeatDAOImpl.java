package dao.daoimpl;

import dao.ISeatDAO;
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
import model.Seat;
import utils.DBContext;

/**
 * 
 * @author Administrator
 * @version 1.0
 */
public class SeatDAOImpl implements ISeatDAO {
    
    private static final Logger logger = Logger.getLogger(SeatDAOImpl.class.getName());

    @Override
    public Optional<Seat> findByID(Long id) {
        String sql = "SELECT * FROM [Seat] WHERE seat_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(seatMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding seat by ID: {0}", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Seat> findAll() {
        List<Seat> seats = new ArrayList<>();
        String sql = "SELECT * FROM [Seat]";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    seats.add(seatMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding all seats: {0}", e.getMessage());
        }
        return seats;
    }

    @Override
    public Long insert(Seat seat) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO [Seat] (flight_id, seat_number, seat_class, status, created_at) ");
        sql.append("VALUES (?, ?, ?, ?, ?)");
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setLong(1, seat.getFlightId());
            ps.setString(2, seat.getSeatNumber());
            ps.setString(3, seat.getSeatClass());
            ps.setString(4, seat.getStatus());
            ps.setTimestamp(5, Timestamp.valueOf(seat.getCreatedAt()));
            
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        seat.setSeatId(rs.getLong(1));
                        return seat.getSeatId();
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting seat: {0}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateByID(Long id, Seat seat) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE [Seat] SET flight_id = ?, seat_number = ?, seat_class = ?, status = ? ");
        sql.append("WHERE seat_id = ?");
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            
            ps.setLong(1, seat.getFlightId());
            ps.setString(2, seat.getSeatNumber());
            ps.setString(3, seat.getSeatClass());
            ps.setString(4, seat.getStatus());
            ps.setLong(5, id);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating seat: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByID(Long id) {
        String sql = "DELETE FROM [Seat] WHERE seat_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting seat: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<Seat> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Seat> findByFlightId(Long flightId) {
        List<Seat> seats = new ArrayList<>();
        String sql = "SELECT * FROM [Seat] WHERE flight_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, flightId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    seats.add(seatMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding seats by flight ID: {0}", e.getMessage());
        }
        return seats;
    }

    @Override
    public List<Seat> findBySeatClass(String seatClass) {
        List<Seat> seats = new ArrayList<>();
        String sql = "SELECT * FROM [Seat] WHERE seat_class = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, seatClass);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    seats.add(seatMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding seats by class: {0}", e.getMessage());
        }
        return seats;
    }

    @Override
    public List<Seat> findByStatus(String status) {
        List<Seat> seats = new ArrayList<>();
        String sql = "SELECT * FROM [Seat] WHERE status = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    seats.add(seatMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding seats by status: {0}", e.getMessage());
        }
        return seats;
    }

    @Override
    public boolean updateStatus(Long seatId, String status) {
        String sql = "UPDATE [Seat] SET status = ? WHERE seat_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ps.setLong(2, seatId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating seat status: {0}", e.getMessage());
            return false;
        }
    }

    private Seat seatMapper(ResultSet rs) throws SQLException {
        Seat seat = new Seat();
        seat.setSeatId(rs.getLong("seat_id"));
        seat.setFlightId(rs.getLong("flight_id"));
        seat.setSeatNumber(rs.getString("seat_number"));
        seat.setSeatClass(rs.getString("seat_class"));
        seat.setStatus(rs.getString("status"));
        seat.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return seat;
    }
} 