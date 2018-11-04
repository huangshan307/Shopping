/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.account;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import sonph.customers.CustomerDAO;
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
    
    public boolean insertAccount(String username, String password, String lastName, String middleName, String firstName, String address, String phone) 
            throws NoSuchAlgorithmException, NamingException, SQLException {
        CustomerDAO daoCust = new CustomerDAO();
        String custID = daoCust.generateCustID(username);
        Connection conn = null;
        PreparedStatement stm1 = null;
        PreparedStatement stm2 = null;
        
        int result = 0;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                String sql1 = "INSERT INTO tbl_account(username, password) VALUES(?, ?)";
                stm1 = conn.prepareStatement(sql1);
                stm1.setString(1, username);
                stm1.setString(2, password);
                result += stm1.executeUpdate();
                String sql2 = "INSERT INTO tbl_customer(custID, lastName, middleName, firstName, address, phone, custLevel, username) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                stm2 = conn.prepareStatement(sql2);
                stm2.setString(1, custID);
                stm2.setString(2, lastName);
                stm2.setString(3, middleName);
                stm2.setString(4, firstName);
                stm2.setString(5, address);
                stm2.setString(6, phone);
                stm2.setInt(7, 1);
                stm2.setString(8, username);
                result += stm2.executeUpdate();
                
                conn.commit();
                return result >= 2;
            }
        } catch(SQLException e) {
            if (conn != null) {
                conn.rollback();
                throw new SQLException(e.getMessage());
            }
        } finally {
            if (stm2 != null) {
                stm2.close();
            }
            if (stm1 != null) {
                stm1.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
}
