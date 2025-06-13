
package dao.daoimpl;

import dao.interfaces.IFeeDAO;
import enums.CalculationType;
import java.util.List;
import model.Fee;
import utils.DBContext;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author manhphong
 */
public class FeeDAOImpl implements IFeeDAO{
    
    private Fee mapResultSetToFee(ResultSet rs) throws SQLException {
        Fee fee = new Fee();
        fee.setConfigId(rs.getLong("config_id"));
        fee.setFeeName(rs.getString("fee_name"));
        fee.setCalculationType(CalculationType.valueOf(rs.getString("calculation_type")));
        fee.setValue(rs.getDouble("value"));
        
        Double minAmount = rs.getDouble("min_amount");
        if (!rs.wasNull()) {
            fee.setMinAmount(minAmount);
        }
        
        Double maxAmount = rs.getDouble("max_amount");
        if (!rs.wasNull()) {
            fee.setMaxAmount(maxAmount);
        }
        fee.setCreatedAt(rs.getTimestamp("created_at"));
        return fee;
    }

    @Override
    public Fee getFeeByName(String feeName) {String sql = "SELECT config_id, fee_name, calculation_type, value, min_amount, max_amount, created_at " +
                    "FROM Agent_Fee_Config WHERE fee_name = ? ";
        
        try (Connection conn = DBContext.getConn(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, feeName);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToFee(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Fee> getAllActiveFees() {
        String sql = "SELECT config_id, fee_name, calculation_type, value, min_amount, max_amount, created_at " +
                    "FROM Agent_Fee_Config ORDER BY fee_name";
        
        List<Fee> fees = new ArrayList<>();
        
        try (Connection conn = DBContext.getConn(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fees.add(mapResultSetToFee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fees;
    }

    @Override
    public boolean updateFee(Fee fee) {
        String sql = "UPDATE Agent_Fee_Config SET fee_name = ?, calculation_type = ?, value = ?, " +
                    "min_amount = ?, max_amount = ? WHERE config_id = ?";
        
        try (Connection conn = DBContext.getConn(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, fee.getFeeName());
            ps.setString(2, fee.getCalculationType().toString());
            ps.setDouble(3, fee.getValue());
            
            if (fee.getMinAmount() != null) {
                ps.setDouble(4, fee.getMinAmount());
            } else {
                ps.setNull(4, Types.DECIMAL);
            }
            
            if (fee.getMaxAmount() != null) {
                ps.setDouble(5, fee.getMaxAmount());
            } else {
                ps.setNull(5, Types.DECIMAL);
            }
            
            ps.setLong(6, fee.getConfigId());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
