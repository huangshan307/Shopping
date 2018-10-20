/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonphse.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import sonphse.utils.DBUtils;

/**
 *
 * @author Huangshan
 */
public class UserDAO implements Serializable {

    public static UserDTO checkLogin(String username, int password) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT userId, password, fullName, role FROM tbl_User WHERE userId = ? AND password = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setInt(2, password);
                rs = stm.executeQuery();
                
                if(rs.next()) {
                    UserDTO dto = new UserDTO();
                    dto.setUserId(rs.getString("userId"));
                    dto.setPassword(rs.getInt("password"));
                    dto.setFullName(rs.getString("fullName"));
                    dto.setRole(rs.getInt("role"));
                    return dto;
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
        
        return null;
    }
}
