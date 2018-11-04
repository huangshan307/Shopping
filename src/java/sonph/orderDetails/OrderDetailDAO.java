/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.orderDetails;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import sonph.utils.DBUtils;

/**
 *
 * @author Huangshan
 */
public class OrderDetailDAO implements Serializable {

    public boolean insertOrderDetail(String productID, String sizesID, int quantity, float unitPrice, float total, String orderID)
            throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        String sql = "INSERT INTO tbl_orderDetail(productID, sizesID, quantity, unitPrice, total, orderID) VALUES(?, ?, ?, ROUND(? , 2), ROUND(?, 2), ?)";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setString(1, productID);
                stm.setString(2, sizesID);
                stm.setInt(3, quantity);
                stm.setFloat(4, unitPrice);
                stm.setFloat(5, total);
                stm.setString(6, orderID);
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
