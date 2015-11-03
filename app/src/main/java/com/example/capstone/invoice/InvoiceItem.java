package com.example.capstone.invoice;

/**
 * Created by Jared on 10/31/2015.
 */
public class InvoiceItem {
    //instance variables
    private int invoiceID;
    private int ItemID;
    private Boolean used;
    private int Lquantity;
    private int Rquantity;
    private int Fquantity;
    private int Bquantity;

    public InvoiceItem(int invoiceID, int itemID, Boolean used, int lquantity, int rquantity,
                       int fquantity, int bquantity) {
        this.invoiceID = invoiceID;
        this.ItemID = itemID;
        this.used = used;
        this.Lquantity = lquantity;
        this.Rquantity = rquantity;
        this.Fquantity = fquantity;
        this.Bquantity = bquantity;
    }

    //setters
    public void setBquantity(int bquantity) {
        Bquantity = bquantity;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public void setLquantity(int lquantity) {
        Lquantity = lquantity;
    }

    public void setRquantity(int rquantity) {
        Rquantity = rquantity;
    }

    public void setFquantity(int fquantity) {
        Fquantity = fquantity;
    }

    //getters
    public int getBquantity() {
        return Bquantity;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public int getItemID() {
        return ItemID;
    }

    public Boolean getUsed() {
        return used;
    }

    public int getLquantity() {
        return Lquantity;
    }

    public int getRquantity() {
        return Rquantity;
    }

    public int getFquantity() {
        return Fquantity;
    }
}
