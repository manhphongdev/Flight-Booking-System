package dao.daoimpl;

import dao.interfaces.IFlightDAO;
import dto.FlightSearchDTO;
import dto.FlightSearchFilter;
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

    private Flight flightMapper(ResultSet rs) throws SQLException {
        Flight flight = new Flight();
        flight.setFlightId(rs.getLong("flight_id"));
        flight.setAirlineId(rs.getLong("airline_id"));
        flight.setEconomyPrice(rs.getDouble("economyprice"));
        flight.setBusinessPrice(rs.getDouble("businessprice"));
        flight.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime() != null ? rs.getTimestamp("created_at").toLocalDateTime() : null);
        flight.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime() != null ? rs.getTimestamp("created_at").toLocalDateTime() : null);
        return flight;
    }

    @Override
    public Optional<Flight> findByID(Long id) {
        String sql = "SELECT * FROM [Flight] WHERE flight_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

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

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

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
        sql.append("INSERT INTO [Flight] (airline_id, economyprice, businessprice, created_at, updated_at) ");
        sql.append("VALUES (?, ?, ?, ?, ?)");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, flight.getAirlineId());
            ps.setDouble(2, flight.getEconomyPrice());
            ps.setDouble(3, flight.getBusinessPrice());
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
        sql.append("UPDATE [Flight] SET airline_id = ?, economyprice = ?, businessprice = ?, ");
        sql.append("updated_at = ? WHERE flight_id = ?");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            ps.setLong(1, flight.getAirlineId());
            ps.setDouble(2, flight.getEconomyPrice());
            ps.setDouble(3, flight.getBusinessPrice());
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

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

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

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

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
        String sql = "SELECT * FROM [Flight] WHERE economy_price BETWEEN ? AND ? OR businessprice BETWEEN ? AND ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

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
        String sql = "UPDATE [Flight] SET economyprice = ?, businessprice = ?, updated_at = GETDATE() WHERE flight_id = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, economyPrice);
            ps.setDouble(2, businessPrice);
            ps.setLong(3, flightId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating flight prices: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<FlightSearchDTO> searchFlightOneWayAndFilterWithCondition(FlightSearchFilter filter) {
        List<FlightSearchDTO> flights = new ArrayList<>();
        String query = buildBaseQuery(filter);
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(query)) {

            int index = 1;

            ps.setString(index++, "%" + filter.getDepartureCity() + "%");
            ps.setString(index++, "%" + filter.getDepartureAirportName() + "%");
            ps.setString(index++, "%" + filter.getArrivalCity() + "%");
            ps.setString(index++, "%" + filter.getArrivalAirportName() + "%");
            ps.setString(index++, filter.getDeparturetime());
            ps.setString(index++, filter.getArrivalTime());

            if (filter.getAirlineName() != null && !filter.getAirlineName().trim().isEmpty()) {
                ps.setString(index++, "%" + filter.getAirlineName() + "%");
            }
            if ("businessClass".equalsIgnoreCase(filter.getTicketType())
                    || "economyClass".equalsIgnoreCase(filter.getTicketType())) {
                ps.setDouble(index++, filter.getMinPrice() != null ? filter.getMinPrice() : 0);
                ps.setDouble(index++, filter.getMaxPrice() != null ? filter.getMaxPrice() : Double.MAX_VALUE);
            }
            // Execute query
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flights.add(new FlightSearchDTO(
                        rs.getLong("flight_id"),
                        rs.getDouble("economyPrice"),
                        rs.getDouble("businessPrice"),
                        rs.getString("airline_name"),
                        rs.getString("airline_code"),
                        rs.getString("departureAirportName"),
                        rs.getString("departureAirportCode"),
                        rs.getString("arrivalAirportName"),
                        rs.getString("arrivalAirportCode"),
                        rs.getString("departureAirportCity"),
                        rs.getString("arrivalAirportCity"),
                        rs.getString("flight_number"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("arrival_time")
                ));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error searching flights: {0}", e.getMessage());
            throw new RuntimeException("Failed to search flights", e);
        }

        return flights;
    }

    /**
     * this class to build a query to search the flight for one trip
     *
     * @param filter
     * @return
     */
    private String buildBaseQuery(FlightSearchFilter filter) {
        StringBuilder sb = new StringBuilder();
        // Build base query
        sb.append("SELECT f.flight_id, f.economyPrice, f.businessPrice, ");
        sb.append("al.airline_name, al.airline_code, ");
        sb.append("ap.airport_code as departureAirportCode, ap.airport_name as departureAirportName, ap.city as departureAirportCity, ");
        sb.append("ap2.airport_code as arrivalAirportCode, ap2.airport_name as arrivalAirportName, ap2.city as arrivalAirportCity, ");
        sb.append("fsl.flight_number, fsl.departure_time, fsl.arrival_time ");
        sb.append("FROM flight f ");
        sb.append("JOIN FlightSegment fs ON f.flight_id = fs.flight_id ");
        sb.append("JOIN Airport ap ON fs.departure_airport = ap.airport_code ");
        sb.append("JOIN Airport ap2 ON fs.arrival_airport = ap2.airport_code ");
        sb.append("JOIN FlightSchedule fsl ON f.flight_id = fsl.flight_id ");
        sb.append("JOIN Airline al ON f.airline_id = al.airline_id ");
        sb.append("WHERE (ap.city LIKE ? OR ap.airport_name LIKE ?) ");
        sb.append("AND (ap2.city LIKE ? OR ap2.airport_name LIKE ?) ");
        sb.append("AND fsl.departure_time >= ? ");
        sb.append("AND fsl.arrival_time <= ? ");
        sb.append("AND fsl.status = 'scheduled' ");

        // Add optional filters
        if (filter.getAirlineName() != null && !filter.getAirlineName().trim().isEmpty()) {
            sb.append("AND al.airline_name LIKE ? ");
        }
        if ("businessClass".equalsIgnoreCase(filter.getTicketType())) {
            sb.append("AND f.businessPrice BETWEEN ? AND ? ");
        } else if ("economyClass".equalsIgnoreCase(filter.getTicketType())) {
            sb.append("AND f.economyPrice BETWEEN ? AND ? ");
        }

        // Add sorting
        sb.append(buildOrder(filter));
        return sb.toString();
    }

    /**
     * build order by price and time start
     *
     * @param priceOrder
     * @param priceOrderType
     * @param timeOrdertype
     * @param ticketType
     * @return
     */
    private String buildOrder(FlightSearchFilter filter) {
        String priceColumn;
        String priceOrder;
        String timeOrder;

        // xac dinh cot gia
        if ("businessClass".equalsIgnoreCase(filter.getTicketType())) {
            priceColumn = "f.businessPrice";
        } else {
            priceColumn = "f.economyPrice";
        }

        // thu tu sap xep gia
        if ("DESC".equalsIgnoreCase(filter.getPriceOrderType())) {
            priceOrder = "DESC";
        } else {
            priceOrder = "ASC";
        }

        // thu tu sap xep theo thoi gian
        if ("DESC".equalsIgnoreCase(filter.getTimeOrdertype())) {
            timeOrder = "DESC";
        } else {
            timeOrder = "ASC";
        }

        return " ORDER BY " + priceColumn + " " + priceOrder + ", fsl.departure_time " + timeOrder;
    }

    @Override
    public List<FlightSearchDTO> searchFlightForBookingOneWay(String departure,
            String arrival, String departureDate) {
        List<FlightSearchDTO> flights = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT f.flight_id, f.economyPrice, f.businessPrice, ");
        sb.append("al.airline_name, al.airline_code, ");
        sb.append("ap.airport_code as departureAirportCode, ap.airport_name as departureAirportName, ap.city as departureAirportCity, ");
        sb.append("ap2.airport_code as arrivalAirportCode, ap2.airport_name as arrivalAirportName, ap2.city as arrivalAirportCity, ");
        sb.append("fsl.flight_number, fsl.departure_time, fsl.arrival_time ");
        sb.append("FROM flight f ");
        sb.append("JOIN FlightSegment fs ON f.flight_id = fs.flight_id ");
        sb.append("JOIN Airport ap ON fs.departure_airport = ap.airport_code ");
        sb.append("JOIN Airport ap2 ON fs.arrival_airport = ap2.airport_code ");
        sb.append("JOIN FlightSchedule fsl ON f.flight_id = fsl.flight_id ");
        sb.append("JOIN Airline al ON f.airline_id = al.airline_id ");
        sb.append("WHERE (ap.airport_name LIKE ? OR ap.city LIKE ?) ");
        sb.append("AND (ap2.airport_name LIKE ? or ap2.city LIKE ?) ");
        sb.append("AND CONVERT(date, fsl.departure_time) = ?");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setString(1, "%" + departure + "%");
            ps.setString(2, "%" + departure + "%");
            ps.setString(3, "%" + arrival + "%");
            ps.setString(4, "%" + arrival + "%");
            ps.setString(5, departureDate);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flights.add(new FlightSearchDTO(
                        rs.getLong("flight_id"),
                        rs.getDouble("economyPrice"),
                        rs.getDouble("businessPrice"),
                        rs.getString("airline_name"),
                        rs.getString("airline_code"),
                        rs.getString("departureAirportName"),
                        rs.getString("departureAirportCode"),
                        rs.getString("arrivalAirportName"),
                        rs.getString("arrivalAirportCode"),
                        rs.getString("departureAirportCity"),
                        rs.getString("arrivalAirportCity"),
                        rs.getString("flight_number"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("arrival_time")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    @Override
    public FlightSearchDTO searchByFlightId(Long id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT f.flight_id, f.economyPrice, f.businessPrice, ");
        sb.append("al.airline_name, al.airline_code, ");
        sb.append("ap.airport_code as departureAirportCode, ap.airport_name as departureAirportName, ap.city as departureAirportCity, ");
        sb.append("ap2.airport_code as arrivalAirportCode, ap2.airport_name as arrivalAirportName, ap2.city as arrivalAirportCity, ");
        sb.append("fsl.flight_number, fsl.departure_time, fsl.arrival_time ");
        sb.append("FROM flight f ");
        sb.append("JOIN FlightSegment fs ON f.flight_id = fs.flight_id ");
        sb.append("JOIN Airport ap ON fs.departure_airport = ap.airport_code ");
        sb.append("JOIN Airport ap2 ON fs.arrival_airport = ap2.airport_code ");
        sb.append("JOIN FlightSchedule fsl ON f.flight_id = fsl.flight_id ");
        sb.append("JOIN Airline al ON f.airline_id = al.airline_id ");
        sb.append("where f.flight_id = ?");

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new FlightSearchDTO(
                        rs.getLong("flight_id"),
                        rs.getDouble("economyPrice"),
                        rs.getDouble("businessPrice"),
                        rs.getString("airline_name"),
                        rs.getString("airline_code"),
                        rs.getString("departureAirportName"),
                        rs.getString("departureAirportCode"),
                        rs.getString("arrivalAirportName"),
                        rs.getString("arrivalAirportCode"),
                        rs.getString("departureAirportCity"),
                        rs.getString("arrivalAirportCity"),
                        rs.getString("flight_number"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("arrival_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        FlightDAOImpl dao = new FlightDAOImpl();

       FlightSearchDTO flight = dao.searchByFlightId(1L);
        System.out.println(flight.toString());
    }
}
