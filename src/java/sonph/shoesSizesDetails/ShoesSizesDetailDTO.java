/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.shoesSizesDetails;

import java.io.Serializable;

/**
 *
 * @author Huangshan
 */
public class ShoesSizesDetailDTO implements Serializable{
    private String shoesID;
    private String sizesID;
    private float price;
    private int quantity;
    
    public ShoesSizesDetailDTO() {
    }

    public ShoesSizesDetailDTO(String shoesID, String sizesID, float price, int quantity) {
        this.shoesID = shoesID;
        this.sizesID = sizesID;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * @return the shoesID
     */
    public String getShoesID() {
        return shoesID;
    }

    /**
     * @param shoesID the shoesID to set
     */
    public void setShoesID(String shoesID) {
        this.shoesID = shoesID;
    }

    /**
     * @return the sizesID
     */
    public String getSizesID() {
        return sizesID;
    }

    /**
     * @param sizesID the sizesID to set
     */
    public void setSizesID(String sizesID) {
        this.sizesID = sizesID;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
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
