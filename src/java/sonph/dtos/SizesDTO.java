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
public class SizesDTO implements Serializable{
    private String id;
    private int sizes;
    private String country;

    public SizesDTO() {
    }

    public SizesDTO(String id, int sizes, String country) {
        this.id = id;
        this.sizes = sizes;
        this.country = country;
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
    
}
