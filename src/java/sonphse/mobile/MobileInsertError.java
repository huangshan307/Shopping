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
public class MobileInsertError implements Serializable{
    private String mobileIdLength;
    private String descriptionLength;
    private String mobileNameLength;
    private String priceNumberFormat;
    private String yearNumberFotmat;
    private String quantityNumberFormat;
    private String mobileIdExist;

    public MobileInsertError() {
    }

    public MobileInsertError(String mobileIdLength, String descriptionLength, String mobileNameLength, String priceNumberFormat, String yearNumberFotmat, String quantityNumberFormat, String mobileIdExist) {
        this.mobileIdLength = mobileIdLength;
        this.descriptionLength = descriptionLength;
        this.mobileNameLength = mobileNameLength;
        this.priceNumberFormat = priceNumberFormat;
        this.yearNumberFotmat = yearNumberFotmat;
        this.quantityNumberFormat = quantityNumberFormat;
        this.mobileIdExist = mobileIdExist;
    }

    /**
     * @return the mobileIdLength
     */
    public String getMobileIdLength() {
        return mobileIdLength;
    }

    /**
     * @param mobileIdLength the mobileIdLength to set
     */
    public void setMobileIdLength(String mobileIdLength) {
        this.mobileIdLength = mobileIdLength;
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
     * @return the mobileNameLength
     */
    public String getMobileNameLength() {
        return mobileNameLength;
    }

    /**
     * @param mobileNameLength the mobileNameLength to set
     */
    public void setMobileNameLength(String mobileNameLength) {
        this.mobileNameLength = mobileNameLength;
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
     * @return the yearNumberFotmat
     */
    public String getYearNumberFotmat() {
        return yearNumberFotmat;
    }

    /**
     * @param yearNumberFotmat the yearNumberFotmat to set
     */
    public void setYearNumberFotmat(String yearNumberFotmat) {
        this.yearNumberFotmat = yearNumberFotmat;
    }

    /**
     * @return the quantityNumberFormat
     */
    public String getQuantityNumberFormat() {
        return quantityNumberFormat;
    }

    /**
     * @param quantityNumberFormat the quantityNumberFormat to set
     */
    public void setQuantityNumberFormat(String quantityNumberFormat) {
        this.quantityNumberFormat = quantityNumberFormat;
    }

    /**
     * @return the mobileIdExist
     */
    public String getMobileIdExist() {
        return mobileIdExist;
    }

    /**
     * @param mobileIdExist the mobileIdExist to set
     */
    public void setMobileIdExist(String mobileIdExist) {
        this.mobileIdExist = mobileIdExist;
    }
}
