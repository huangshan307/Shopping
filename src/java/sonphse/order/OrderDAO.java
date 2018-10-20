/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonphse.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import javax.naming.NamingException;
import sonphse.cart.Item;
import sonphse.mobile.MobileDAO;
import sonphse.mobile.MobileDTO;
import sonphse.utils.DBUtils;

/**
 *
 * @author Huangshan
 */
public class OrderDAO implements Serializable {

    private int orderID;

    public int getOrderID() {
        return orderID;
    }

    /**
     *
     * @param dto
     * @return Return Order id
     * @throws java.sql.SQLException
     * @throws javax.naming.NamingException
     */
    public int insertOrder(OrderDTO dto) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                //String key[] = {"id"};
                String sql = "INSERT INTO tbl_Order(userId, orderDate) VALUES(?, ?)";
                stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1, dto.getUserId());
                stm.setDate(2, dto.getOrderDate());
                stm.executeUpdate();
                rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
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
        return -1;
    }

    /**
     *
     * @param orderId
     * @return
     * @throws java.sql.SQLException
     * @throws javax.naming.NamingException
     */
    public boolean deleteOrder(int orderId) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "DELETE FROM tbl_Order WHERE id = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, orderId);

                return stm.executeUpdate() > 0;
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

    /**
     *
     * @param lsItem
     * @param bill
     * @throws SQLException
     * @throws NamingException
     */
    public void inserOrderAndDetail(Map<String, Item> lsItem, OrderDTO bill) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stmOrder = null;
        PreparedStatement stmDetail = null;
        PreparedStatement stmMobile = null;
        ResultSet rs = null;
        String sqlOrder = "INSERT INTO tbl_Order(userId, orderDate) VALUES(?, ?)";
        String sqlMobile = "UPDATE tbl_Mobile SET quantity = quantity - ? WHERE mobileId = ? AND quantity >= 0";
        String sqlDetail = "INSERT INTO tbl_OrderDetail(orderId, mobileId, quantity) VALUES(?, ?, ?)";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                // Insert order to tbl_Order
                stmOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
                stmOrder.setString(1, bill.getUserId());
                stmOrder.setDate(2, bill.getOrderDate());
                stmOrder.executeUpdate();
                rs = stmOrder.getGeneratedKeys();
                if (rs.next()) {
                    this.orderID = rs.getInt(1);
                    bill.setId(this.orderID);
                }
                // update quantity into tbl_Mobile
                stmMobile = conn.prepareStatement(sqlMobile);
                for (Map.Entry<String, Item> e : lsItem.entrySet()) {
                    int quantity = e.getValue().getQuantity();
                    stmMobile.setInt(1, quantity);
                    stmMobile.setString(2, e.getKey());
                    stmMobile.executeUpdate();
                }
                // Insert orderDetail
                stmDetail = conn.prepareStatement(sqlDetail);
                for (Map.Entry<String, Item> e : lsItem.entrySet()) {
                    stmDetail.setInt(1, this.orderID);
                    stmDetail.setString(2, e.getKey());
                    stmDetail.setInt(3, e.getValue().getQuantity());

                    stmDetail.executeUpdate();
                }

                conn.commit();
            }
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
                throw new SQLException(e.getMessage());
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmDetail != null) {
                stmDetail.close();
            }
            if (stmMobile != null) {
                stmMobile.close();
            }
            if (stmOrder != null) {
                stmOrder.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
