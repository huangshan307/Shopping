/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.sizes;

import java.io.Serializable;

/**
 *
 * @author Huangshan
 */
public class SizesDTO implements Serializable{
    private String id;
    private String shoesID;
    private int sizes;
    private String country;
    private float price;
    private int quantity;

    public SizesDTO() {
    }

    public SizesDTO(String id, String shoesID, int sizes, String country, float price, int quantity) {
        this.id = id;
        this.shoesID = shoesID;
        this.sizes = sizes;
        this.country = country;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the sizes
     */
    public int getSizes() {
        return sizes;
    }

    /**
     * @param sizes the sizes to set
     */
    public void setSizes(int sizes) {
        this.sizes = sizes;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
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
