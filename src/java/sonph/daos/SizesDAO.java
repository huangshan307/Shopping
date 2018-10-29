/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sonph.dtos.SizesDTO;
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
    
    public SizesDTO searchSizes (String sizesID) throws NamingException, SQLException {
        SizesDTO sizes = new SizesDTO();
        
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT id, sizes, country FROM tbl_sizes WHERE id = ?";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setString(1, sizesID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    sizes.setId(rs.getString("id"));
                    sizes.setSizes(rs.getInt("sizes"));
                    sizes.setCountry(rs.getString("country"));
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
}
