/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.dtos;

import java.io.Serializable;

/**
 *
 * @author Huangshan
 */
public class ShoesDTO implements Serializable{
    private String shoesID;
    private String description;
    private float price;
    private int quantity;

    public ShoesDTO() {
    }

    public ShoesDTO(String shoesID, String description, float price, int quantity) {
        this.shoesID = shoesID;
        this.description = description;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
