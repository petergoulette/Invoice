package com.example.capstone.invoice;

/**
 * Created by Pierro on 10/29/2015.
 */
public class Item {

    private int itemId;
    private String itemName;
    private int itemQuantity;

    public Item (){}

    public Item (int id, String name, int quantity){
        itemId=id;
        itemName=name;
        itemQuantity=quantity;
    }

    public Item (String name, int quantity){
        itemName=name;
        itemQuantity=quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }


}
