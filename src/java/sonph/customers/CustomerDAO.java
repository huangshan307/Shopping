/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.customers;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import sonph.utils.DBUtils;
import sonph.utils.MD5Text;

/**
 *
 * @author Huangshan
 */
public class CustomerDAO implements Serializable{
    
    public String generateCustID(String username) throws NoSuchAlgorithmException {
        String hashText = MD5Text.getHashText(username);
        String id = "";
        for (int i = 0; i < 10; i++) {
            id += hashText.charAt(i);
        }
        return id;
    }
    
    public CustomerDTO getCustomer(String username) throws NamingException, SQLException {
        CustomerDTO dto = new CustomerDTO();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tbl_customer WHERE username = ?";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    dto.setCustID(rs.getString("custID"));
                    dto.setLastName(rs.getString("lastName"));
                    dto.setMiddleName(rs.getString("middleName"));
                    dto.setFirstName(rs.getString("firstName"));
                    dto.setAddress(rs.getString("address"));
                    dto.setPhone(rs.getString("phone"));
                    dto.setCustLevel(rs.getInt("custLevel"));
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
