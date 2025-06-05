package dao.daoimpl;

import dao.IAirlineDAO;
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
import model.Airline;
import utils.DBContext;

/**
 *
 * @author Administrator
 * @version 1.0
 */
public class AirlineDAOImpl implements IAirlineDAO {

    private static final Logger LOG = Logger.getLogger(AirlineDAOImpl.class.getName());

    private Airline airlineMapper(ResultSet rs) throws SQLException {
        return new Airline(rs.getLong("airline_id"),
                rs.getString("airline_name"),
                rs.getString("airline_code"),
                rs.getTimestamp("created_at").toLocalDateTime());
    }

    @Override
    public List<Airline> findAll() {
        List<Airline> lists = new ArrayList<>();
        String sql = "Select * from [Airline]";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                try {
                    lists.add(airlineMapper(rs));
                } catch (SQLException ex) {
                    Logger.getLogger(AirlineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    public boolean insert(Airline entity) {
        String sql = "INSERT INTO [Airline] (airline_code, airline_name, created_at) VALUES (?, ?, ?)";
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getAirlineCode());
            ps.setString(2, entity.getAirlineName());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "airline insert failed: {0}", e.getMessage());
        }
        LOG.log(Level.WARNING, "airline insert failed, entity is exist, code: ", entity.getAirlineCode());
        return false;
    }

    @Override
    public Optional<Airline> findByCode(String code) {
        String sql = "Select * from [Airline] where airline_code = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(airlineMapper(rs));
            }
        } catch (SQLException e) {
            LOG.log(Level.INFO, "airline not found: {0}", e.getMessage());
        }
        LOG.log(Level.WARNING, "airline not found: {0}", code);
        return Optional.empty();
    }

    @Override
    public boolean deleteByCode(String code) {
        String sql = "delete from [Airline] where airline_code = ?";
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            LOG.log(Level.WARNING, "delete failed, code: {0} ", code);
        }
        LOG.log(Level.WARNING, "delete failed, code: {0} ", code);
        return false;
    }

    @Override
    public List<Airline> findByName(String airlineName) {
        List<Airline> airlines = new ArrayList<>();
        String sql = "SELECT * FROM [Airline] WHERE airline_name LIKE ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + airlineName + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    airlines.add(airlineMapper(rs));
                }
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error finding airlines by name: {0}", e.getMessage());
        }
        return airlines;
    }

    public static void main(String[] args) {
        AirlineDAOImpl dao = new AirlineDAOImpl();
        dao.insert(new Airline( "AB", "ABC", LocalDateTime.now()));
    }
}
