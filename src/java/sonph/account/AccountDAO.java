/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import sonph.utils.DBUtils;

/**
 *
 * @author Huangshan
 */
public class AccountDAO implements Serializable{
    public AccountDTO checkLogin(String username, String password) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT username, password FROM tbl_account WHERE username = ? AND password = ?";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    AccountDTO dto = new AccountDTO();
                    dto.setUsername(rs.getString("username"));
                    dto.setPassword(rs.getString("password"));
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
