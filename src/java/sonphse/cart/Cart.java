/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonphse.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import sonphse.mobile.MobileDTO;

/**
 *
 * @author Huangshan
 */
public class Cart implements Serializable{
    private String customID;
    private Map<String, Item> items; // <mobileId, item>

    public Cart() {
    }

    public Cart(String customID, Map<String, Item> items) {
        this.customID = customID;
        this.items = items;
    }

    /**
     * @return the customID
     */
    public String getCustomID() {
        return customID;
    }

    /**
     * @param customID the customID to set
     */
    public void setCustomID(String customID) {
        this.customID = customID;
    }

    /**
     * @return the items
     */
    public Map<String, Item> getItems() {
        return items;
    }

    /**
     * @param mobile
     */
    public void addToCart(MobileDTO mobile) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        Item item = new Item();
        item.setMobile(mobile); // set default mobile
        item.setQuantity(1); // set default quantity = 1 when add brand new product
        String mobileId = mobile.getMobileId();
        if (this.items.containsKey(mobileId)) {
            // when product has exist in cart
            item = this.items.get(mobileId);
            item.setQuantity(item.getQuantity() + 1);
        }
        
        this.items.put(mobileId, item);
    }
    
    /**
     * 
     * @param mobileID
     */
    public void removeFromCart(String mobileID) {
        if (this.items != null && !this.items.isEmpty()) {
            if (this.items.containsKey(mobileID)) {
                this.items.remove(mobileID);
            }
        }
    }
    
    public float getTotalFullPrice() {
        float result = 0;
        if (this.items != null && !this.items.isEmpty()) {
            result = this.items.keySet().stream()
                    .map((key) -> this.items.get(key).getTotalPrice())
                    .reduce(result, (accumulator, _item) -> accumulator + _item);
        }
        return result;
    }
}
