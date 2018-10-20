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
public class MobileSearchUserError implements Serializable {
    private String minNumberFormat;
    private String maxNumberFormat;

    public MobileSearchUserError() {
    }

    public MobileSearchUserError(String minNumberFormat, String maxNumberFormat) {
        this.minNumberFormat = minNumberFormat;
        this.maxNumberFormat = maxNumberFormat;
    }

    /**
     * @return the minNumberFormat
     */
    public String getMinNumberFormat() {
        return minNumberFormat;
    }

    /**
     * @param minNumberFormat the minNumberFormat to set
     */
    public void setMinNumberFormat(String minNumberFormat) {
        this.minNumberFormat = minNumberFormat;
    }

    /**
     * @return the maxNumberFormat
     */
    public String getMaxNumberFormat() {
        return maxNumberFormat;
    }

    /**
     * @param maxNumberFormat the maxNumberFormat to set
     */
    public void setMaxNumberFormat(String maxNumberFormat) {
        this.maxNumberFormat = maxNumberFormat;
    }
}
