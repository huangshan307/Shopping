/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonphse.order;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Huangshan
 */
public class OrderDTO implements Serializable{
    private int id;
    private String userId;
    private Date orderDate;

    public OrderDTO() {
    }

    public OrderDTO(int id, String userId, Date orderDate) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
