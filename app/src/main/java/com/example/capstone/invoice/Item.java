package com.example.capstone.invoice;

/**
 * Created by Pierro on 10/29/2015.
 */
public class Item {

    private int itemId;
    private String itemName;
    private int itemRate;

    public Item (){}

    public Item (int id, String name, int Rate){
        this.itemId=id;
        this.itemName=name;
        this.itemRate=Rate;
    }

    public Item (String name, int Rate){
        this.itemName=name;
        this.itemRate=Rate;
    }

    public int getItemId() {
        return this.itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemRate() {
        return this.itemRate;
    }

    public void setItemRate(int itemRate) {
        this.itemRate = itemRate;
    }
}
