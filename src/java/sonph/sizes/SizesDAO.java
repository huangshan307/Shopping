/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.sizes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sonph.shoesSizesDetails.ShoesSizesDetailDTO;
import sonph.utils.DBUtils;

/**
 *
 * @author Huangshan
 */
public class SizesDAO implements Serializable{
    private List<SizesDTO> list;

    /**
     * @return the list
     */
    public List<SizesDTO> getList() {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        return list;
    }
    
    public SizesDTO searchSizes(ShoesSizesDetailDTO shoesSizes) throws NamingException, SQLException {
        SizesDTO sizes = new SizesDTO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT id, sizes, country FROM tbl_sizes WHERE id = ?";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setString(1, shoesSizes.getSizesID());
                rs = stm.executeQuery();
                if (rs.next()) {
                    
                    sizes.setId(rs.getString("id"));
                    sizes.setSizes(rs.getInt("sizes"));
                    sizes.setCountry(rs.getString("country"));
                    sizes.setPrice(shoesSizes.getPrice());
                    sizes.setQuantity(shoesSizes.getQuantity());
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
        return sizes;
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
        String sql = "SELECT id, sizes, country, price, quantity FROM tbl_sizes WHERE shoesID = ?";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setString(1, shoesID);
                rs = stm.executeQuery();
                while(rs.next()) {
                    SizesDTO sizes = new SizesDTO();
                    sizes.setId(rs.getString("id"));
                    sizes.setSizes(rs.getInt("sizes"));
                    sizes.setCountry(rs.getString("country"));
                    sizes.setPrice(rs.getFloat("price"));
                    sizes.setQuantity(rs.getInt("Quantity"));
                    
                    this.list.add(sizes);
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
    
    
}
