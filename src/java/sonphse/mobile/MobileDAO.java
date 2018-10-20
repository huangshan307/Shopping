/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonphse.mobile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sonphse.utils.DBUtils;

/**
 *
 * @author Huangshan
 */
public class MobileDAO {

    List<MobileDTO> list;

    public List<MobileDTO> searchById(String id) throws NamingException, SQLException {
        if (list == null) {
            list = new ArrayList<>();
        }
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT mobileId, description, price, mobileName, yearOfProduction, quantity, notSale"
                        + " FROM tbl_Mobile WHERE mobileId LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + id + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    MobileDTO row = new MobileDTO();
                    row.setMobileId(rs.getString("mobileId"));
                    row.setDescription(rs.getString("description"));
                    row.setPrice(rs.getFloat("price"));
                    row.setMobileName(rs.getString("mobileName"));
                    row.setYearOfProduction(rs.getInt("yearOfProduction"));
                    row.setQuantity(rs.getInt("quantity"));
                    row.setNotSale(rs.getBoolean("notSale"));

                    list.add(row);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<MobileDTO> searchByName(String name) throws SQLException, NamingException {
        if (list == null) {
            list = new ArrayList<>();
        }
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT mobileId, description, price, mobileName, yearOfProduction, quantity, notSale"
                        + " FROM tbl_Mobile WHERE mobileName LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    MobileDTO row = new MobileDTO();
                    row.setMobileId(rs.getString("mobileId"));
                    row.setDescription(rs.getString("description"));
                    row.setPrice(rs.getFloat("price"));
                    row.setMobileName(rs.getString("mobileName"));
                    row.setYearOfProduction(rs.getInt("yearOfProduction"));
                    row.setQuantity(rs.getInt("quantity"));
                    row.setNotSale(rs.getBoolean("notSale"));

                    list.add(row);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean deleteMobile(String id) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "DELETE FROM tbl_Mobile WHERE mobileId = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);

                int result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean updateMobile(String id, String description, float price, int quantity, boolean notSale) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "UPDATE tbl_Mobile SET description = ?, price = ?, quantity = ?, notSale = ? WHERE mobileId = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, description);
                stm.setFloat(2, price);
                stm.setInt(3, quantity);
                stm.setBoolean(4, notSale);
                stm.setString(5, id);
                
                int result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
    
    public boolean insertMobile(String mobileId, String description, float price, String mobileName, int yearOfProduction, int quantity, boolean notSale) 
        throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;        
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO tbl_Mobile VALUES(?, ?, ?, ?, ?, ?, ?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, mobileId);
                stm.setString(2, description);
                stm.setFloat(3, price);
                stm.setString(4, mobileName);
                stm.setInt(5, yearOfProduction);
                stm.setInt(6, quantity);
                stm.setBoolean(7, notSale);
                
                int result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }
            } 
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
    
    public List<MobileDTO> searchWithPrice(float min, float max) throws SQLException, NamingException {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (min > max) {
            float tmp = min;
            min = max;
            max = tmp;
        }
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT * FROM tbl_Mobile WHERE price BETWEEN ? AND ? AND quantity >= 1 Order By price";
                stm = conn.prepareStatement(sql);
                stm.setFloat(1, min);
                stm.setFloat(2, max);
                rs = stm.executeQuery();
                while(rs.next()) {
                    MobileDTO row = new MobileDTO();
                    row.setMobileId(rs.getString("mobileId"));
                    row.setDescription(rs.getString("description"));
                    row.setPrice(rs.getFloat("price"));
                    row.setMobileName(rs.getString("mobileName"));
                    row.setYearOfProduction(rs.getInt("yearOfProduction"));
                    row.setQuantity(rs.getInt("quantity"));
                    row.setNotSale(rs.getBoolean("notSale"));
                    
                    list.add(row);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return list;
    }
    
    public MobileDTO getMobileById(String mobileId) throws SQLException, NamingException {
        MobileDTO dto = new MobileDTO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT * FROM tbl_Mobile WHERE mobileId = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, mobileId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    dto.setMobileId(rs.getString("mobileId"));
                    dto.setDescription(rs.getString("description"));
                    dto.setPrice(rs.getFloat("price"));
                    dto.setMobileName(rs.getString("mobileName"));
                    dto.setYearOfProduction(rs.getInt("yearOfProduction"));
                    dto.setQuantity(rs.getInt("quantity"));
                    dto.setNotSale(rs.getBoolean("notSale"));
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return dto;
    }
    
    public int getQuantity(String mobileId) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT quantity FROM tbl_Mobile WHERE mobileId = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, mobileId);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("quantity");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return -1;
    }
}
