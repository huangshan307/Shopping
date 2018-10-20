/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonphse.orderdetail;

import java.io.Serializable;

/**
 *
 * @author Huangshan
 */
public class OrderDetailDTO implements Serializable{
    private int id;
    private int orderId;
    private String mobileId;
    private int quantity;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int id, int orderId, String mobileId, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.mobileId = mobileId;
        this.quantity = quantity;
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
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the mobileId
     */
    public String getMobileId() {
        return mobileId;
    }

    /**
     * @param mobileId the mobileId to set
     */
    public void setMobileId(String mobileId) {
        this.mobileId = mobileId;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
