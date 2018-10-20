/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonphse.cart;

import java.io.Serializable;
import sonphse.mobile.MobileDTO;

/**
 *
 * @author Huangshan
 */
public class Item implements Serializable {
    private MobileDTO mobile;
    private int quantity;

    public Item() {
    }

    public Item(MobileDTO mobile, int quantity) {
        this.mobile = mobile;
        this.quantity = quantity;
    }

    /**
     * @return the mobile
     */
    public MobileDTO getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(MobileDTO mobile) {
        this.mobile = mobile;
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
    
    public float getTotalPrice() {
        return this.mobile.getPrice() * quantity;
    }
}
