/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonphse.orderdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import sonphse.cart.Item;
import sonphse.mobile.MobileDAO;
import sonphse.mobile.MobileDTO;
import sonphse.order.OrderDAO;
import sonphse.order.OrderDTO;
import sonphse.utils.DBUtils;

/**
 *
 * @author Huangshan
 */
public class OrderDetailDAO implements Serializable {

    public boolean insertOrderDetail(Map<String, Item> listItems, OrderDTO bill) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        int count = 0;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                String sql = "INSERT INTO tbl_OrderDetail(orderId, mobileId, quantity) VALUES(?, ?, ?)";
                stm = conn.prepareStatement(sql);
                for (String key : listItems.keySet()) {
                    MobileDAO mbDao = new MobileDAO();
                    MobileDTO mbDto = mbDao.getMobileById(key);

                    if (mbDto != null) {
                        int quantity = mbDto.getQuantity() - listItems.get(key).getQuantity();

                        if (quantity >= 0) {
                            mbDao.updateMobile(mbDto.getMobileId(), mbDto.getDescription(), mbDto.getPrice(), quantity, mbDto.isNotSale());

                            stm.setInt(1, bill.getId());
                            stm.setString(2, key);
                            stm.setInt(3, listItems.get(key).getQuantity());

                            count += stm.executeUpdate();
                            conn.commit();
                        } else {
                            throw new SQLException("The quantity of \"" + key + "\"");
                        }
                    }
                } // end for loop
                return count > 0;
            } // end if conn
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw new SQLException(e.getMessage());
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
