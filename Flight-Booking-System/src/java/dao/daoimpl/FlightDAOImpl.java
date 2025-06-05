package dao.daoimpl;

import dao.IFlightDAO;
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
import model.Flight;
import utils.DBContext;

/**
 * 
 * @author Administrator
 * @version 1.0
 */
public class FlightDAOImpl implements IFlightDAO {
    
    private static final Logger logger = Logger.getLogger(FlightDAOImpl.class.getName());

    @Override
    public Optional<Flight> findByID(Long id) {
        String sql = "SELECT * FROM [Flight] WHERE flight_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(flightMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding flight by ID: {0}", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Flight> findAll() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM [Flight]";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    flights.add(flightMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding all flights: {0}", e.getMessage());
        }
        return flights;
    }

    @Override
    public Long insert(Flight flight) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO [Flight] (airline_id, economy_price, business_price, created_at, updated_at) ");
        sql.append("VALUES (?, ?, ?, ?, ?)");
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setLong(1, flight.getAirlineId());
            ps.setBigDecimal(2, flight.getEconomyPrice());
            ps.setBigDecimal(3, flight.getBusinessPrice());
            ps.setTimestamp(4, Timestamp.valueOf(flight.getCreatedAt()));
            ps.setTimestamp(5, Timestamp.valueOf(flight.getUpdatedAt()));
            
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        flight.setFlightId(rs.getLong(1));
                        return flight.getFlightId();
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting flight: {0}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateByID(Long id, Flight flight) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE [Flight] SET airline_id = ?, economy_price = ?, business_price = ?, ");
        sql.append("updated_at = ? WHERE flight_id = ?");
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            
            ps.setLong(1, flight.getAirlineId());
            ps.setBigDecimal(2, flight.getEconomyPrice());
            ps.setBigDecimal(3, flight.getBusinessPrice());
            ps.setTimestamp(4, Timestamp.valueOf(flight.getUpdatedAt()));
            ps.setLong(5, id);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating flight: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByID(Long id) {
        String sql = "DELETE FROM [Flight] WHERE flight_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error delete flight: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<Flight> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Flight> findByAirlineId(Long airlineId) {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM [Flight] WHERE airline_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, airlineId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    flights.add(flightMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error find flights: {0}", e.getMessage());
        }
        return flights;
    }

    @Override
    public List<Flight> findByPriceRange(double minPrice, double maxPrice) {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM [Flight] WHERE economy_price BETWEEN ? AND ? OR business_price BETWEEN ? AND ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);
            ps.setDouble(3, minPrice);
            ps.setDouble(4, maxPrice);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    flights.add(flightMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error findi fligh: {0}", e.getMessage());
        }
        return flights;
    }

    @Override
    public boolean updatePrices(Long flightId, double economyPrice, double businessPrice) {
        String sql = "UPDATE [Flight] SET economy_price = ?, business_price = ?, updated_at = GETDATE() WHERE flight_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setDouble(1, economyPrice);
            ps.setDouble(2, businessPrice);
            ps.setLong(3, flightId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating flight prices: {0}", e.getMessage());
            return false;
        }
    }

    private Flight flightMapper(ResultSet rs) throws SQLException {
        Flight flight = new Flight();
        flight.setFlightId(rs.getLong("flight_id"));
        flight.setAirlineId(rs.getLong("airline_id"));
        flight.setEconomyPrice(rs.getBigDecimal("economy_price"));
        flight.setBusinessPrice(rs.getBigDecimal("business_price"));
        flight.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        flight.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return flight;
    }
} 