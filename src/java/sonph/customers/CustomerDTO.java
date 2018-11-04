/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.customers;

import java.io.Serializable;

/**
 *
 * @author Huangshan
 */
public class CustomerDTO implements Serializable{
    private String custID;
    private String lastName;
    private String middleName;
    private String firstName;
    private String address;
    private String phone;
    private int custLevel;
    private String username;

    public CustomerDTO() {
    }

    public CustomerDTO(String custID, String lastName, String middleName, String firstName, String address, String phone, int custLevel, String username) {
        this.custID = custID;
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstName = firstName;
        this.address = address;
        this.phone = phone;
        this.custLevel = custLevel;
        this.username = username;
    }

    /**
     * @return the custID
     */
    public String getCustID() {
        return custID;
    }

    /**
     * @param custID the custID to set
     */
    public void setCustID(String custID) {
        this.custID = custID;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the custLevel
     */
    public int getCustLevel() {
        return custLevel;
    }

    /**
     * @param custLevel the custLevel to set
     */
    public void setCustLevel(int custLevel) {
        this.custLevel = custLevel;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
}
