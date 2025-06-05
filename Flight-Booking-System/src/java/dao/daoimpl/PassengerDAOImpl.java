package dao.daoimpl;

import dao.IPassengerDAO;
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
import model.Passenger;
import utils.DBContext;

/**
 *
 * @author Administrator
 * @version 1.0
 */
public class PassengerDAOImpl implements IPassengerDAO {

    private static final Logger logger = Logger.getLogger(PassengerDAOImpl.class.getName());

    @Override
    public Optional<Passenger> findByID(Long id) {
        String sql = "SELECT * FROM [Passenger] WHERE passenger_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(passengerMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding passenger by ID: {0}", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Passenger> findAll() {
        List<Passenger> passengers = new ArrayList<>();
        String sql = "SELECT * FROM [Passenger]";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    passengers.add(passengerMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding all passengers: {0}", e.getMessage());
        }
        return passengers;
    }

    @Override
    public Long insert(Passenger passenger) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO [Passenger] (user_id, first_name, last_name, date_of_birth, ");
        sql.append("gender, nationality, identity_type, identity_number, created_at) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, passenger.getUserId());
            ps.setString(2, passenger.getFirstName());
            ps.setString(3, passenger.getLastName());
            ps.setDate(4, java.sql.Date.valueOf(passenger.getDateOfBirth()));
            ps.setString(5, passenger.getGender());
            ps.setString(6, passenger.getNationality());
            ps.setString(7, passenger.getIdentityType());
            ps.setString(8, passenger.getIdentityNumber());
            ps.setTimestamp(9, Timestamp.valueOf(passenger.getCreatedAt()));

            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        passenger.setPassengerId(rs.getLong(1));
                        return passenger.getPassengerId();
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting passenger: {0}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateByID(Long id, Passenger passenger) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE [Passenger] SET user_id = ?, first_name = ?, last_name = ?, ");
        sql.append("date_of_birth = ?, gender = ?, nationality = ?, identity_type = ?, ");
        sql.append("identity_number = ? WHERE passenger_id = ?");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            ps.setLong(1, passenger.getUserId());
            ps.setString(2, passenger.getFirstName());
            ps.setString(3, passenger.getLastName());
            ps.setDate(4, java.sql.Date.valueOf(passenger.getDateOfBirth()));
            ps.setString(5, passenger.getGender());
            ps.setString(6, passenger.getNationality());
            ps.setString(7, passenger.getIdentityType());
            ps.setString(8, passenger.getIdentityNumber());
            ps.setLong(9, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating passenger: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByID(Long id) {
        String sql = "DELETE FROM [Passenger] WHERE passenger_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting passenger: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<Passenger> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Passenger> findByUserId(Long userId) {
        List<Passenger> passengers = new ArrayList<>();
        String sql = "SELECT * FROM [Passenger] WHERE user_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    passengers.add(passengerMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding passengers by user ID: {0}", e.getMessage());
        }
        return passengers;
    }

    @Override
    public List<Passenger> findByIdentityNumber(String identityNumber) {
        List<Passenger> passengers = new ArrayList<>();
        String sql = "SELECT * FROM [Passenger] WHERE identity_number = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, identityNumber);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    passengers.add(passengerMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding passengers by identity number: {0}", e.getMessage());
        }
        return passengers;
    }

    @Override
    public List<Passenger> findByNationality(String nationality) {
        List<Passenger> passengers = new ArrayList<>();
        String sql = "SELECT * FROM [Passenger] WHERE nationality = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nationality);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    passengers.add(passengerMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding passengers by nationality: {0}", e.getMessage());
        }
        return passengers;
    }

    private Passenger passengerMapper(ResultSet rs) throws SQLException {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(rs.getLong("passenger_id"));
        passenger.setUserId(rs.getLong("user_id"));
        passenger.setFirstName(rs.getString("first_name"));
        passenger.setLastName(rs.getString("last_name"));
        passenger.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        passenger.setGender(rs.getString("gender"));
        passenger.setNationality(rs.getString("nationality"));
        passenger.setIdentityType(rs.getString("identity_type"));
        passenger.setIdentityNumber(rs.getString("identity_number"));
        passenger.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return passenger;
    }
}
