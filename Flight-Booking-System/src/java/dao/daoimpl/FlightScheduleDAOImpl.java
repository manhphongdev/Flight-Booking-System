package dao.daoimpl;

import dao.interfaces.IFlightScheduleDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FlightSchedule;
import utils.DBContext;

/**
 *
 * @author Administrator
 * @version 1.0
 */
public class FlightScheduleDAOImpl implements IFlightScheduleDAO {

    private static final Logger logger = Logger.getLogger(FlightScheduleDAOImpl.class.getName());

    @Override
    public Optional<FlightSchedule> findByID(Long id) {
        String sql = "SELECT * FROM [FlightSchedule] WHERE schedule_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(flightScheduleMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding flight schedule by ID: {0}", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<FlightSchedule> findAll() {
        List<FlightSchedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM [FlightSchedule]";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    schedules.add(flightScheduleMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding all flight schedules: {0}", e.getMessage());
        }
        return schedules;
    }

    @Override
    public Long insert(FlightSchedule schedule) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO [FlightSchedule] (flight_id, flight_number, departure_time, arrival_time, ");
        sql.append("duration, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, schedule.getFlightId());
            ps.setString(2, schedule.getFlightNumber());
            ps.setTimestamp(3, Timestamp.valueOf(schedule.getDepartureTime()));
            ps.setTimestamp(4, Timestamp.valueOf(schedule.getArrivalTime()));
            ps.setInt(5, schedule.getDuration());
            ps.setString(6, schedule.getStatus());
            ps.setTimestamp(7, Timestamp.valueOf(schedule.getCreatedAt()));
            ps.setTimestamp(8, Timestamp.valueOf(schedule.getUpdatedAt()));

            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        schedule.setScheduleId(rs.getLong(1));
                        return schedule.getScheduleId();
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting flight schedule: {0}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateByID(Long id, FlightSchedule schedule) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE [FlightSchedule] SET flight_id = ?, flight_number = ?, departure_time = ?, ");
        sql.append("arrival_time = ?, duration = ?, status = ?, updated_at = ? WHERE schedule_id = ?");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            ps.setLong(1, schedule.getFlightId());
            ps.setString(2, schedule.getFlightNumber());
            ps.setTimestamp(3, Timestamp.valueOf(schedule.getDepartureTime()));
            ps.setTimestamp(4, Timestamp.valueOf(schedule.getArrivalTime()));
            ps.setInt(5, schedule.getDuration());
            ps.setString(6, schedule.getStatus());
            ps.setTimestamp(7, Timestamp.valueOf(schedule.getUpdatedAt()));
            ps.setLong(8, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating flight schedule: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByID(Long id) {
        String sql = "DELETE FROM [FlightSchedule] WHERE schedule_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting flight schedule: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<FlightSchedule> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<FlightSchedule> findByFlightId(Long flightId) {
        List<FlightSchedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM [FlightSchedule] WHERE flight_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, flightId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    schedules.add(flightScheduleMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding flight schedules by flight ID: {0}", e.getMessage());
        }
        return schedules;
    }

    @Override
    public List<FlightSchedule> findByStatus(String status) {
        List<FlightSchedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM [FlightSchedule] WHERE status = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    schedules.add(flightScheduleMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding flight schedules by status: {0}", e.getMessage());
        }
        return schedules;
    }

    @Override
    public List<FlightSchedule> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<FlightSchedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM [FlightSchedule] WHERE departure_time BETWEEN ? AND ? OR arrival_time BETWEEN ? AND ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(startDate));
            ps.setTimestamp(2, Timestamp.valueOf(endDate));
            ps.setTimestamp(3, Timestamp.valueOf(startDate));
            ps.setTimestamp(4, Timestamp.valueOf(endDate));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    schedules.add(flightScheduleMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding flight schedules by date range: {0}", e.getMessage());
        }
        return schedules;
    }

    @Override
    public boolean updateStatus(Long scheduleId, String status) {
        String sql = "UPDATE [FlightSchedule] SET status = ?, updated_at = GETDATE() WHERE schedule_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setLong(2, scheduleId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating flight schedule status: {0}", e.getMessage());
            return false;
        }
    }

    private FlightSchedule flightScheduleMapper(ResultSet rs) throws SQLException {
        FlightSchedule schedule = new FlightSchedule();
        schedule.setScheduleId(rs.getLong("schedule_id"));
        schedule.setFlightId(rs.getLong("flight_id"));
        schedule.setFlightNumber(rs.getString("flight_number"));
        schedule.setDepartureTime(rs.getTimestamp("departure_time").toLocalDateTime());
        schedule.setArrivalTime(rs.getTimestamp("arrival_time").toLocalDateTime());
        schedule.setDuration(rs.getInt("duration"));
        schedule.setStatus(rs.getString("status"));
        schedule.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        schedule.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return schedule;
    }

    @Override
    public String getAirlineNameByFlightId(Long flightId) {
        StringBuilder sb = new StringBuilder("Select a.airline_name From Airline a ");
        sb.append("join Flight f on f.airline_id = a.airline_id ");
        sb.append("where f.flight_id = ?");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setLong(1, flightId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAirlineCodeByFlightId(Long flightId) {
        StringBuilder sb = new StringBuilder("Select a.airline_code From Airline a ");
        sb.append("join Flight f on f.airline_id = a.airline_id ");
        sb.append("where f.flight_id = ?");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setLong(1, flightId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<FlightSchedule> findByDepartureTime(String date) {
        List<FlightSchedule> list = new ArrayList<>();
        String sql = "SELECT * FROM FlightSchedule "
                + "WHERE CONVERT(DATE, departure_time) = CONVERT(DATE, ?)";
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,   date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(flightScheduleMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static void main(String[] args) {
        FlightScheduleDAOImpl fs = new FlightScheduleDAOImpl();
        List<FlightSchedule> flightSchedules = fs.findByDepartureTime("06-06-2025");
        for (FlightSchedule flightSchedule : flightSchedules) {
            System.out.println(flightSchedule);
        }
    }
}
