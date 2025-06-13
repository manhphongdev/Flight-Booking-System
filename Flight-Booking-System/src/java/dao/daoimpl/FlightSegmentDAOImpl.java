package dao.daoimpl;

import dao.interfaces.IFlightSegmentDAO;
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
import model.FlightSegment;
import utils.DBContext;

/**
 *
 * @author Administrator
 * @version 1.0
 */
public class FlightSegmentDAOImpl implements IFlightSegmentDAO {

    private static final Logger logger = Logger.getLogger(FlightSegmentDAOImpl.class.getName());

    @Override
    public Optional<FlightSegment> findByID(Long id) {
        String sql = "SELECT * FROM [FlightSegment] WHERE segment_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(flightSegmentMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding flight segment by ID: {0}", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<FlightSegment> findAll() {
        List<FlightSegment> segments = new ArrayList<>();
        String sql = "SELECT * FROM [FlightSegment]";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    segments.add(flightSegmentMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding all flight segments: {0}", e.getMessage());
        }
        return segments;
    }

    @Override
    public Long insert(FlightSegment segment) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO [FlightSegment] (flight_id, departure_airport, arrival_airport, ");
        sql.append(" created_at) VALUES (?, ?, ?, ?)");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, segment.getFlightId());
            ps.setString(2, segment.getDepartureAirport());
            ps.setString(3, segment.getArrivalAirport());
            ps.setTimestamp(4, Timestamp.valueOf(segment.getCreatedAt()));

            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        segment.setSegmentId(rs.getLong(1));
                        return segment.getSegmentId();
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting flight segment: {0}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateByID(Long id, FlightSegment segment) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE [FlightSegment] SET flight_id = ?, departure_airport = ?, arrival_airport = ? ");
        sql.append(" WHERE segment_id = ?");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            ps.setLong(1, segment.getFlightId());
            ps.setString(2, segment.getDepartureAirport());
            ps.setString(3, segment.getArrivalAirport());
            ps.setLong(4, segment.getSegmentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating flight segment: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByID(Long id) {
        String sql = "DELETE FROM [FlightSegment] WHERE segment_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting flight segment: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<FlightSegment> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<FlightSegment> findByFlightId(Long flightId) {
        List<FlightSegment> segments = new ArrayList<>();
        String sql = "SELECT * FROM [FlightSegment] WHERE flight_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, flightId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    segments.add(flightSegmentMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding flight segments by flight ID: {0}", e.getMessage());
        }
        return segments;
    }

    @Override
    public List<FlightSegment> findByAirport(String airportCode) {
        List<FlightSegment> segments = new ArrayList<>();
        String sql = "SELECT * FROM [FlightSegment] WHERE departure_airport = ? OR arrival_airport = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, airportCode);
            ps.setString(2, airportCode);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    segments.add(flightSegmentMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding flight segments by airport: {0}", e.getMessage());
        }
        return segments;
    }

    @Override
    public List<FlightSegment> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        List<FlightSegment> segments = new ArrayList<>();
        String sql = "SELECT * FROM [FlightSegment] WHERE departure_time BETWEEN ? AND ? OR arrival_time BETWEEN ? AND ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(startTime));
            ps.setTimestamp(2, Timestamp.valueOf(endTime));
            ps.setTimestamp(3, Timestamp.valueOf(startTime));
            ps.setTimestamp(4, Timestamp.valueOf(endTime));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    segments.add(flightSegmentMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding flight segments by time range: {0}", e.getMessage());
        }
        return segments;
    }

    private FlightSegment flightSegmentMapper(ResultSet rs) throws SQLException {
        FlightSegment segment = new FlightSegment();
        segment.setSegmentId(rs.getLong("segment_id"));
        segment.setFlightId(rs.getLong("flight_id"));
        segment.setDepartureAirport(rs.getString("departure_airport"));
        segment.setArrivalAirport(rs.getString("arrival_airport"));
        segment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return segment;
    }

    @Override
    public FlightSegment findByDepartureAirportAndArrvalAirport(String departure, String arrival) {
        String sql = "select * from FlightSegment where departure_airport = ? and arrival_airport = ?";
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, departure);
            ps.setString(2, arrival);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return flightSegmentMapper(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

}
