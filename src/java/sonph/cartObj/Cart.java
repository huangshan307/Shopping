/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.cartObj;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import sonph.shoes.ShoesDTO;
import sonph.sizes.SizesDTO;

/**
 *
 * @author Huangshan
 */
public class Cart implements Serializable {

    private float totalPrice;
    private Map<String, Map<String, Item>> list;

    /**
     * @return the list
     */
    public Map<String, Map<String, Item>> getList() {
        if (this.list == null) {
            this.list = new HashMap<>();
        }
        return list;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     *
     */
    public float getTotalPrice() {
        float total = 0;
        for (String shoesID : this.list.keySet()) {
            Map<String, Item> listItem = this.list.get(shoesID);
            for (String sizesID : listItem.keySet()) {
                Item item = listItem.get(sizesID);
                total += item.getPrice();
            }
        }

        return total;
    }

    /**
     *
     * @param shoes
     * @param sizes
     * @throws javax.naming.NamingException
     * @throws java.sql.SQLException
     */
    public void addToCart(ShoesDTO shoes, SizesDTO sizes) throws NamingException, SQLException {
        if (this.list == null) {
            this.list = new HashMap<>();
        }
        Item item = new Item(shoes, sizes, 1);
        if (this.list.containsKey(shoes.getShoesID())) {
            Map<String, Item> listItem = this.list.get(shoes.getShoesID());
            if (listItem.containsKey(sizes.getId())) {
                item = listItem.get(sizes.getId());
                int quantity = item.getQuantity();
                item.setQuantity(quantity + 1);
            } else {
                listItem.put(sizes.getId(), item);
            }
        } else {
            Map<String, Item> listItem = new HashMap<>();
            listItem.put(sizes.getId(), item);
            this.list.put(shoes.getShoesID(), listItem);
        }
    }

    /**
     *
     * @param shoesID
     * @param sizesID
     */
    public void removeFromCart(String shoesID, String sizesID) {
        if (this.list != null) {
            if (this.list.containsKey(shoesID)) {
                Map<String, Item> listItem = this.list.get(shoesID);
                if (listItem.containsKey(sizesID)) {
                    listItem.remove(sizesID);
                }
                if (listItem.isEmpty()) {
                    this.list.remove(shoesID);
                }
            }
        }
    }
}
