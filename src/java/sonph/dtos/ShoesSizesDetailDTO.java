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
public class ShoesSizesDetailDTO implements Serializable{

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
    private String shoesID;
    private String sizesID;

    public ShoesSizesDetailDTO() {
    }

    public ShoesSizesDetailDTO(String shoesID, String sizesID) {
        this.shoesID = shoesID;
        this.sizesID = sizesID;
    }
    
}
