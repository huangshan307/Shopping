/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.shoesSizesDetails;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sonph.utils.DBUtils;

/**
 *
 * @author Huangshan
 */
public class ShoesSizesDetailDAO implements Serializable{
    private List<ShoesSizesDetailDTO> list;

    /**
     * @return the list
     */
    public List<ShoesSizesDetailDTO> getList() {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        return list;
    }
    
    /**
     * 
     * @param shoesID
     * @throws NamingException
     * @throws SQLException 
     */
    public void searchWithShoesID(String shoesID) throws NamingException, SQLException {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT shoesID, sizesID, price, quantity FROM tbl_shoesSizesDetail WHERE shoesID = ?";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setString(1, shoesID);
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    ShoesSizesDetailDTO dto = new ShoesSizesDetailDTO();
                    dto.setShoesID(rs.getString("shoesID"));
                    dto.setSizesID(rs.getString("sizesID"));
                    dto.setPrice(rs.getFloat("price"));
                    dto.setQuantity(rs.getInt("quantity"));
                    
                    this.list.add(dto);
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
    }
    
    /**
     * 
     * @param shoesID
     * @param sizesID
     * @return
     * @throws NamingException
     * @throws SQLException 
     */
    public ShoesSizesDetailDTO getWithShoesIDSizesId(String shoesID, String sizesID) throws NamingException, SQLException {
        ShoesSizesDetailDTO dto = new ShoesSizesDetailDTO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT shoesID, sizesID, price, quantity FROM tbl_shoesSizesDetail WHERE shoesID = ? AND sizesID = ?";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setString(1, shoesID);
                stm.setString(2, sizesID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    dto.setShoesID(rs.getString("shoesID"));
                    dto.setSizesID(rs.getString("sizesID"));
                    dto.setPrice(rs.getFloat("price"));
                    dto.setQuantity(rs.getInt("quantity"));
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
        return dto;
    }
    
    public boolean updateWithShoesIDSizesID(String shoesID, String sizesID, float price, int quantity) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        String sql = "UPDATE tbl_shoesSizesDetail SET price = ?, quantity = ? WHERE shoesID = ? AND sizesID = ?";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setFloat(1, price);
                stm.setInt(2, quantity);
                stm.setString(3, shoesID);
                stm.setString(4, sizesID);
                
                return stm.executeUpdate() > 0;
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
}
