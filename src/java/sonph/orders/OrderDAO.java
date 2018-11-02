/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.orders;

import java.io.Serializable;

/**
 *
 * @author Huangshan
 */
public class OrderDAO implements Serializable{
    private String generateCode() {
        String code = "";
        long currentDate = System.currentTimeMillis();
        return code;
    }
}
