/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.shoes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sonph.shoes.ShoesDTO;
import sonph.utils.DBUtils;

/**
 *
 * @author Huangshan
 */
public class ShoesDAO implements Serializable{
    private List<ShoesDTO> list;

    /**
     * @return the list
     */
    public List<ShoesDTO> getList() {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        return list;
    }
    
    /**
     * 
     * @param shoesID
     * @return 
     * @throws javax.naming.NamingException 
     * @throws java.sql.SQLException 
     */
    public List<ShoesDTO> search(String shoesID) throws NamingException, SQLException {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        String sql = "SELECT shoesID, description, quantity FROM tbl_shoes WHERE description LIKE ?";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + shoesID + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    ShoesDTO dto = new ShoesDTO();
                    dto.setShoesID(rs.getString("shoesID"));
                    dto.setDescription(rs.getString("description"));
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
        
        return list;
    }
    
    /**
     * 
     * @return 
     * @param shoesID
     * @throws javax.naming.NamingException 
     * @throws java.sql.SQLException
     */
    public ShoesDTO getShoes(String shoesID) throws NamingException, SQLException {
        ShoesDTO dto = new ShoesDTO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT shoesID, description, quantity FROM tbl_shoes WHERE shoesID = ?";
        try {
             conn = DBUtils.makeConnection();
             if (conn != null) {
                 stm = conn.prepareStatement(sql);
                 stm.setString(1, shoesID);
                 rs = stm.executeQuery();
                 if (rs.next()) {
                     dto.setShoesID(rs.getString("shoesID"));
                     dto.setDescription(rs.getString("description"));
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
}
