/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.orders;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import sonph.utils.DBUtils;
import sonph.utils.MD5Text;

/**
 *
 * @author Huangshan
 */
public class OrderDAO implements Serializable {

    

    public String generateCode() throws NoSuchAlgorithmException{
        long currentDate = System.currentTimeMillis();
        
        String md5msg = MD5Text.getHashText(String.valueOf(currentDate));
        String code = "";
        for (int i = 0; i < 10; i++) {
            code += md5msg.charAt(i);
        }
        return code;
    }
    
    public boolean insertOrder(String code, String custID, float total) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        String sql = "INSERT INTO tbl_order(orderID, orderDate, custID, total) VALUES(?, DEFAULT, ?, ROUND(?, 2))";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setString(1, code);
                stm.setString(2, custID);
                stm.setFloat(3, total);
                int result = stm.executeUpdate();
                return result > 0;
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
