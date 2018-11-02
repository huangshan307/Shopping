/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.cartObj;

import java.io.Serializable;
import sonph.shoes.ShoesDTO;
import sonph.sizes.SizesDTO;

/**
 *
 * @author Huangshan
 */
public class Item implements Serializable{
    private ShoesDTO shoes;
    private SizesDTO sizes;
    private int quantity;
    
    private float price;
    
    public Item() {
    }

    public Item(ShoesDTO shoes, SizesDTO sizes, int quantity) {
        this.shoes = shoes;
        this.sizes = sizes;
        this.quantity = quantity;
    }

    /**
     * @return the shoes
     */
    public ShoesDTO getShoes() {
        return shoes;
    }

    /**
     * @param shoes the shoes to set
     */
    public void setShoes(ShoesDTO shoes) {
        this.shoes = shoes;
    }

    /**
     * @return the sizes
     */
    public SizesDTO getSizes() {
        return sizes;
    }

    /**
     * @param sizes the sizes to set
     */
    public void setSizes(SizesDTO sizes) {
        this.sizes = sizes;
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
    
    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return this.sizes.getPrice() * this.quantity;
    }
    
}
