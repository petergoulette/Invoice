package com.example.capstone.invoice;

import java.util.Comparator;

/**
 * Created by Pierro on 10/29/2015.
 */
public class Item implements Comparable<Item>{

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

    @Override
    public int compareTo(Item another) {

        int compareQuantity = ((Item) another).getItemRate();
        return this.itemRate - compareQuantity;
    }

    public static Comparator<Item> ItemNameComparator
            = new Comparator<Item>() {

        public int compare(Item item1, Item item2) {

            String itemName1 = item1.getItemName().toUpperCase();
            String itemName2 = item2.getItemName().toUpperCase();

            //ascending order
            return itemName1.compareTo(itemName2);

            //descending order
            //return fruitName2.compareTo(fruitName1);
        }
    };
}

