/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonphse.user;

import java.io.Serializable;

/**
 *
 * @author Huangshan
 */
public class LoginError implements Serializable{
    private String passwordNumberFormat;
    private String usernameOrPassword;

    public LoginError() {
    }

    public LoginError(String passwordNumberFormat, String usernameOrPassword) {
        this.passwordNumberFormat = passwordNumberFormat;
        this.usernameOrPassword = usernameOrPassword;
    }

    /**
     * @return the passwordNumberFormat
     */
    public String getPasswordNumberFormat() {
        return passwordNumberFormat;
    }

    /**
     * @param passwordNumberFormat the passwordNumberFormat to set
     */
    public void setPasswordNumberFormat(String passwordNumberFormat) {
        this.passwordNumberFormat = passwordNumberFormat;
    }

    /**
     * @return the usernameOrPassword
     */
    public String getUsernameOrPassword() {
        return usernameOrPassword;
    }

    /**
     * @param usernameOrPassword the usernameOrPassword to set
     */
    public void setUsernameOrPassword(String usernameOrPassword) {
        this.usernameOrPassword = usernameOrPassword;
    }
    
}
