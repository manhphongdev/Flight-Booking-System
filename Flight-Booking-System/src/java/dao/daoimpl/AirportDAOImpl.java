/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.daoimpl;

import dao.IAirportDAO;
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
import model.Airport;
import utils.DBContext;

/**
 * 
 * @author Administrator
 * @version 1.0
 */
public class AirportDAOImpl implements IAirportDAO {

    private static final Logger LOG = Logger.getLogger(AirportDAOImpl.class.getName());

    private Airport airportMapper(ResultSet rs) throws SQLException {
    return new Airport(
        rs.getString("airport_code"),
        rs.getString("airport_name"),
        rs.getString("city"),
        rs.getString("country"),
        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
        rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null    
    );
}

    @Override
    public List<Airport> findAll() {
        List<Airport> lists = new ArrayList<>();
        String sql = "Select * from [Airport]";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                try {
                    lists.add(airportMapper(rs));
                } catch (SQLException ex) {
                    Logger.getLogger(AirportDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    public boolean insert(Airport entity) {
        String sql = "INSERT INTO [Airport] (airport_code, airport_name, city, country, created_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getAirportCode());
            ps.setString(2, entity.getAirportName());
            ps.setString(3, entity.getCity());
            ps.setString(4, entity.getCountry());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "airport insert faild: {0}", e.getMessage());
        }
        LOG.log(Level.WARNING, "airport insert faild, entity is exist, code: ", entity.getAirportCode());
        return false;
    }

    @Override
    public Optional<Airport> findByCode(String code) {

        String sql = "Select * from [Airport] where airport_code = ?";

        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(airportMapper(rs));
            }
        } catch (SQLException e) {
            LOG.log(Level.INFO, "airport not found: {0}", e.getMessage());
        }
        LOG.log(Level.WARNING, "airport not found: {0}", code);
        return Optional.empty();
    }

    @Override
    public boolean updateByCode( Airport entity) {
        StringBuilder sb = new StringBuilder();
        sb.append("update [Airport] set airport_name = ?, city = ?, country = ?, updated_at = ? ");
        sb.append("where airport_code = ?");
        try (Connection conn = DBContext.getConn(); PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            ps.setString(1, entity.getAirportName());
            ps.setString(2, entity.getCity());
            ps.setString(3, entity.getCountry());
            ps.setTimestamp(4, Timestamp.valueOf(entity.getUpdateAt()));
            ps.setString(5, entity.getAirportCode());
            if (ps.executeUpdate() == 1) {
                LOG.log(Level.INFO, "airport updated, code: {0}", entity.getAirportCode());
                return true;
            }
        } catch (SQLException e) {
            LOG.log(Level.WARNING, "update failed, code: {0} ", entity.getAirportCode());
        }
        LOG.log(Level.WARNING, "update failed, code: {0} ", entity.getAirportCode());
        return false;
    }

    @Override
    public boolean deleteByCode(String code) {

        String sql = "delete from [Airport] where airport_code = ?";
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

}
