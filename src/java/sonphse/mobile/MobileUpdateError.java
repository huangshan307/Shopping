/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonphse.mobile;

import java.io.Serializable;

/**
 *
 * @author Huangshan
 */
public class MobileUpdateError implements Serializable{
    private String descriptionLength;
    private String priceNumberFormat;
    private String quanlityNumberFormat;

    public MobileUpdateError() {
    }

    public MobileUpdateError(String descriptionLength, String priceNumberFormat, String quanlityNumberFormat) {
        this.descriptionLength = descriptionLength;
        this.priceNumberFormat = priceNumberFormat;
        this.quanlityNumberFormat = quanlityNumberFormat;
    }

    /**
     * @return the descriptionLength
     */
    public String getDescriptionLength() {
        return descriptionLength;
    }

    /**
     * @param descriptionLength the descriptionLength to set
     */
    public void setDescriptionLength(String descriptionLength) {
        this.descriptionLength = descriptionLength;
    }

    /**
     * @return the priceNumberFormat
     */
    public String getPriceNumberFormat() {
        return priceNumberFormat;
    }

    /**
     * @param priceNumberFormat the priceNumberFormat to set
     */
    public void setPriceNumberFormat(String priceNumberFormat) {
        this.priceNumberFormat = priceNumberFormat;
    }

    /**
     * @return the quanlityNumberFormat
     */
    public String getQuanlityNumberFormat() {
        return quanlityNumberFormat;
    }

    /**
     * @param quanlityNumberFormat the quanlityNumberFormat to set
     */
    public void setQuanlityNumberFormat(String quanlityNumberFormat) {
        this.quanlityNumberFormat = quanlityNumberFormat;
    }
}
